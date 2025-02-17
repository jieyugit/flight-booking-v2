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
    private Integer flightId;
    private Map<String, List<Integer>> seat;
}
