package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;


import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat implements Serializable {
    private String flightId;
    private Integer left;
    private Map<String, List<String>> seat;



}
