package top.johnnycse.flight;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.johnnycse.flight.service.Seats.SeatService;

import java.math.BigInteger;

@SpringBootTest
public class SeatTest {
    @Autowired
    private SeatService service;
    @Test
    void test(){
        System.out.println(service.getSeatsByFlightId(Long.parseLong("546739344405303299")));
    }
}
