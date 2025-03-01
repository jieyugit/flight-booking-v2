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
    @Autowired
    private SeatService seatService;
    @Test
    void test(){
        //System.out.println(service.getAvailableSeatsByFlightIdAndClass(Long.parseLong("546739344405303299"), 2));
        //System.out.println(service.getSeatsByFlightId(Long.parseLong("546739344405303299")));
        //System.out.println(service.updateSeatBookingStatus(Long.parseLong("546739344405303299"), Long.parseLong("1892836309714735107") ,"1B" ,false));
        //System.out.println(service.getAvailableSeatsByFlightIdAndClass(Long.parseLong("546739344405303299"), 2));
        //System.out.println(service.getAvailableSeatsByFlightIdAndClass(Long.parseLong("546739344405303299"), 1));
        //System.out.println(service.getSeatsByFlightId(Long.parseLong("546739344405303299")));


        System.out.println(seatService.getSeatBySeatNumber(Long.parseLong("546739344405303299"),"1B"));
    }
}
