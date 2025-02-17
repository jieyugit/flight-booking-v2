package top.johnnycse.flight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("top.johnnycse.flight.repository")
public class FlightApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightApplication.class, args);
    }

}
