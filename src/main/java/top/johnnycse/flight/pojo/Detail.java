package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    String name;
    String age;
    String phone_number;
    String email;
    String address;
    Integer user_id;
    Integer flight_id;
    Integer status;
}
