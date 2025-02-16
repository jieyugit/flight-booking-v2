package top.johnnycse.flight.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String  userName;
    @JsonIgnore
    private String password;
    private Integer type;
    @JsonIgnore
    private Integer status;
    private String phone;
    private String email;
    @JsonIgnore
    private Integer deleted;
}
