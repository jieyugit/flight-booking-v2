package top.johnnycse.flight.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import top.johnnycse.flight.pojo.Cabin_Seats;
import top.johnnycse.flight.pojo.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends MongoRepository<Cabin_Seats, String> {
    // 查询某个航班的所有座位
    Optional<Cabin_Seats> findByFlightId(long flightId);

    // 查询某个舱位类别的座位
    @Query("{'seats.cabin_class': ?0}")
    List<Cabin_Seats> findByCabinClass(int cabinClass);

    // 查询某个座位编号的座位
    @Query("{'seats.seat_number': ?0}")
    Cabin_Seats findBySeatNumber(String seatNumber);

    @Query("{'flight_id': ?0, 'seats.cabin_class': ?1, 'seats.is_booked': false}")
    List<Cabin_Seats> findAvailableSeatsByFlightIdAndClass(long flightId, int cabinClass);

    // 根据航班号、舱位ID和座位号更新座位的预订状态
    @Modifying
    @Query("{'flight_id': ?0, 'seats.cabin_id': ?1, 'seats.seat_number': ?2}")
    boolean updateSeatBookingStatus(long flightId, long cabinId, String seatNumber, boolean isBooked);


}
