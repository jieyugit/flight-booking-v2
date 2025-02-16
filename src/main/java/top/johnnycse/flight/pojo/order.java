package top.johnnycse.flight.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class order {
    Integer id;
    String name;
    Integer age;
    String phone_number;
    String email;
    String address;
    Integer user_id;
    User user;
    Integer flight_id;
    Flight flight;
    Integer status;

}
