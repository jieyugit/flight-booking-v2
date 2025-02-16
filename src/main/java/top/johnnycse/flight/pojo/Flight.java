package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private String id;
    private String flight_code;
    private String depart;
    private String destn;
    private String depart_Time;
    private String destn_Time;
    private Double price;
    private String airline;
    private Integer ticketLeft;

}
