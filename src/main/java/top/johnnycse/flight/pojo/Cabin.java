package top.johnnycse.flight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cabin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cabin {
    @Id
    @Column(name = "cabin_id")
    private Long cabinId;

    @Column(name = "flight_id", nullable = false)
    private Long flightId;

    @Column(name = "cabin_class", nullable = false)
    private Integer cabinClass;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "remaining_seats", nullable = false)
    private Integer remainingSeats;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Version
    private Long version;
}
