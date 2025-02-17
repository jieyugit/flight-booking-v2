package top.johnnycse.flight.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airline")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
    @Id
    @Column(name = "airline_id")
    private Integer airlineId;

    @Column(name = "airline_code", nullable = false, unique = true)
    private String airlineCode;

    @Column(name = "airline_name", nullable = false)
    private String airlineName;
}
