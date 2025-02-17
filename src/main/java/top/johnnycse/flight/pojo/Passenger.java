package top.johnnycse.flight.pojo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
public class Passenger {
    @Id
    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "id_type", nullable = false)
    private Integer idType; // 0-身份证 1-护照

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(name = "passenger_type", nullable = false)
    private Integer passengerType; // 0-成人 1-儿童 2-婴儿

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;
}
