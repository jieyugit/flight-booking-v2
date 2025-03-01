package top.johnnycse.flight.service.Flight.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.pojo.Cabin;
import top.johnnycse.flight.pojo.Cabin_Seats;
import top.johnnycse.flight.pojo.Seat;
import top.johnnycse.flight.repository.CabinRepository;
import top.johnnycse.flight.service.Flight.CabinService;
import top.johnnycse.flight.service.Seats.SeatService;

import java.util.List;

@Service
public class CabinServiceImpl implements CabinService {
    @Autowired
    private SeatService seatService;
    @Autowired
    private CabinRepository cabinRepository;

    @Override
    public boolean deductCabin(Long flightId, Integer count) {

        return false;
    }

    //TODO: 引入Kafka消息队列 解决分布式事务问题
    @Override
    public boolean reserveCabin(Long flightId, String seatNumber) {
        //查询nosql中的座位信息
        Cabin_Seats cabin_seats = seatService.getSeatBySeatNumber(flightId,seatNumber);
        if(cabin_seats.getSeats().get(0).isBooked()){
            throw new RuntimeException("Seat is already booked");
        }
        //更新nosql中的座位信息
        Seat seat = cabin_seats.getSeats().get(0);
        long cabinId = seat.getCabinId();

        seatService.updateSeatBookingStatus(flightId,cabinId,seatNumber,true);
        //使用乐观锁更新mysql中的座位信息

        return false;

    }


}
