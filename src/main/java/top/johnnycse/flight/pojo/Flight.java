package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "airline_code", nullable = false)
    private String airlineCode;

    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "aircraft_model", nullable = false)
    private String aircraftModel;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer status; // 0-取消 1-正常
}
