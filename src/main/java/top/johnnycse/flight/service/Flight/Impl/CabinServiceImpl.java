package top.johnnycse.flight.service.Flight.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.pojo.Cabin;
import top.johnnycse.flight.pojo.Cabin_Seats;
import top.johnnycse.flight.pojo.Seat;
import top.johnnycse.flight.repository.CabinRepository;
import top.johnnycse.flight.service.Flight.CabinService;
import top.johnnycse.flight.service.Seats.SeatService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CabinServiceImpl implements CabinService {
    @Autowired
    private SeatService seatService;
    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "flight-tx-topic";

    @Override
    public boolean deductCabin(Long flightId, Integer count) {

        return false;
    }

    @Override
    public boolean reserveCabin(Long flightId, String seatNumber) {
        //查询nosql中的座位信息
        Cabin_Seats cabin_seats = seatService.getSeatBySeatNumber(flightId, seatNumber);
        Seat seat = cabin_seats.getSeats().get(0);

        if (seat.isBooked()) {
            throw new RuntimeException("Seat is already booked");
        }

        boolean isUpdated = seatService.updateSeatBookingStatus(flightId, seat.getCabinId(), seatNumber, true);
        if (!isUpdated) {
            throw new RuntimeException("MongoDB update failed");
        }

        long cabinId = seat.getCabinId();
        String message = "cabinId:" + cabinId + ",seatNumber:" + seatNumber;
//        try {
//            SendResult<String, String> sendResult = kafkaTemplate.send(TOPIC, message).get(5, TimeUnit.SECONDS);
//            if (sendResult.getRecordMetadata() == null) {
//                throw new RuntimeException("Kafka message delivery failed");
//            }
//        } catch (Exception e) {
//            seatService.updateSeatBookingStatus(flightId, seat.getCabinId(), seatNumber, false);
//            throw new RuntimeException("Kafka send failed, rollback MongoDB", e);
//        }
//
//        return true;
        for (int i = 0; i < 3; i++) {
            try {
                SendResult<String, String> sendResult = kafkaTemplate.send(TOPIC, message).get(5, TimeUnit.SECONDS);
                if (sendResult.getRecordMetadata() != null) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Kafka send failed, retrying... (" + (i + 1) + "/3)");
            }
        }

        seatService.updateSeatBookingStatus(flightId, seat.getCabinId(), seatNumber, false);
        throw new RuntimeException("Kafka send failed after retries, rollback MongoDB");

    }


}
