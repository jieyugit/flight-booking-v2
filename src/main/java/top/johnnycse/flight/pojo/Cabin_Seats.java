package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "seats")
public class Cabin_Seats {
    @Id
    private String id;  // MongoDB的_id
    @Field("aircraft_model")
    private String aircraftModel;  // 飞机型号
    @Field("flight_id")
    private long flightId;  // 航班ID
    private List<Seat> seats;  // 座位列表
}
