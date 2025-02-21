package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import top.johnnycse.flight.enums.FlightClass;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Field("cabin_id")
    private long cabinId;  // 用long来表示NumberLong
    @Field("seat_number")
    private String seatNumber;
    @Field("cabin_class")
    private Integer cabinClass;  // 0 - 经济舱, 1 - 商务舱, 2 - 头等舱
    @Field("is_booked")
    private boolean isBooked;
}
