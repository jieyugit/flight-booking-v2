import pymysql
import pymongo
import time

mysql_conn = pymysql.connect(
    host="****",
    user="root",
    password="***",
    database="flights",
    cursorclass=pymysql.cursors.DictCursor
)

mongo_client = pymongo.MongoClient("mongodb://***:27017/", username="***", password="***")
mongo_db = mongo_client["flight_system"]
mongo_seats_collection = mongo_db["seats"]


class SnowflakeIDGenerator:
    def __init__(self, datacenter_id=1, worker_id=1, sequence=0):
        self.twepoch = 1288834974657  # 时间起点（毫秒）
        self.datacenter_id_bits = 5
        self.worker_id_bits = 5
        self.sequence_bits = 12

        self.datacenter_id = datacenter_id
        self.worker_id = worker_id
        self.sequence = sequence

        self.max_datacenter_id = -1 ^ (-1 << self.datacenter_id_bits)
        self.max_worker_id = -1 ^ (-1 << self.worker_id_bits)
        self.sequence_mask = -1 ^ (-1 << self.sequence_bits)

        self.last_timestamp = -1

    def _current_time_millis(self):
        return int(time.time() * 1000)

    def _wait_for_next_millis(self, last_timestamp):
        timestamp = self._current_time_millis()
        while timestamp <= last_timestamp:
            timestamp = self._current_time_millis()
        return timestamp

    def generate_id(self):
        timestamp = self._current_time_millis()

        if timestamp < self.last_timestamp:
            raise Exception("时钟回拨，拒绝生成ID")

        if timestamp == self.last_timestamp:
            self.sequence = (self.sequence + 1) & self.sequence_mask
            if self.sequence == 0:
                timestamp = self._wait_for_next_millis(self.last_timestamp)
        else:
            self.sequence = 0

        self.last_timestamp = timestamp

        unique_id = ((timestamp - self.twepoch) << (self.datacenter_id_bits + self.worker_id_bits + self.sequence_bits)) | \
                    (self.datacenter_id << (self.worker_id_bits + self.sequence_bits)) | \
                    (self.worker_id << self.sequence_bits) | \
                    self.sequence
        return unique_id

# === 统一座位布局 ===
SEAT_LAYOUT = {
    "first_class": {"rows": 5, "seats_per_row": 4},  # 头等舱：5排，每排4个座位
    "business_class": {"rows": 4, "seats_per_row": 5},  # 商务舱：4排，每排5个座位
    "economy_class": {"rows": 33, "seats_per_row": 6},  # 经济舱：33排，每排6个座位
}

base_price_mapping = {
    "economy_class": 500.00,  # 经济舱基础价格
    "business_class": 1500.00,  # 商务舱基础价格
    "first_class": 3000.00  # 头等舱基础价格
}

cabin_class_mapping = {
    "economy_class": 0,
    "business_class": 1,
    "first_class": 2
}



with mysql_conn.cursor() as cursor:
    cursor.execute("SELECT flight_id, aircraft_model FROM flight;")
    flights = cursor.fetchall()

generator = SnowflakeIDGenerator()
cabin_data = []
mongo_bulk_insert = []

for flight in flights:
    flight_id = flight["flight_id"]
    aircraft_model = flight["aircraft_model"]

    seat_number = 1
    row_number = 1
    seat_list = []

    # 生成头等舱座位信息
    for cabin_class, config in SEAT_LAYOUT.items():
        # seat_number = 1
        total_seats = config["rows"] * config["seats_per_row"]
        remaining_seats = total_seats
        cabin_id = generator.generate_id()

        cabin_class_num = cabin_class_mapping[cabin_class]
        base_price = base_price_mapping[cabin_class]

        cabin_data.append((cabin_id, flight_id, cabin_class_num, total_seats, remaining_seats,base_price))

        seats = []
        seat_number = 1
        for row in range(1, config["rows"] + 1):

            for seat_index in range(config["seats_per_row"]):
                if seat_number > total_seats:
                    break
                # seat_id = generator.generate_id()
                seat_info = {
                    "cabin_id": cabin_id,
                    "seat_number": f"{row_number}{chr(65 + seat_index)}",  # 生成座位号格式：行号+列号，例如 1A、1B、1C
                    "cabin_class": cabin_class_num,
                    "is_booked": False
                }
                seats.append(seat_info)
                seat_number += 1
            row_number += 1

        seat_list.extend(seats)


    mongo_bulk_insert.append({
        "flight_id": flight_id,
        "aircraft_model": aircraft_model,
        "seats": seat_list
    })

with mysql_conn.cursor() as cursor:
    insert_query = """
    INSERT INTO cabin (cabin_id, flight_id, cabin_class, total_seats, remaining_seats,base_price)
    VALUES (%s, %s, %s, %s, %s, %s);
    """
    cursor.executemany(insert_query, cabin_data)
    mysql_conn.commit()

if mongo_bulk_insert:
    mongo_seats_collection.insert_many(mongo_bulk_insert)

print("Cabin 和 MongoDB 座位数据已成功生成！")

mysql_conn.close()
mongo_client.close()
