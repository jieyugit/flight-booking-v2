package top.johnnycse.flight.service.Seats.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.pojo.Cabin_Seats;
import top.johnnycse.flight.pojo.Seat;
import top.johnnycse.flight.repository.SeatRepository;
import top.johnnycse.flight.service.Seats.SeatService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;


    @Override
    public Cabin_Seats getSeatsByFlightId(long flightId) {
        Optional<Cabin_Seats> cabinSeats = seatRepository.findByFlightId(flightId);

        if (cabinSeats.isPresent()) {
            return cabinSeats.get();
        } else {
            throw new RuntimeException("No seats found for flightId: " + flightId);
        }
    }

    @Override
    public List<Cabin_Seats> getSeatsByCabinClass(int cabinClass) {
        return seatRepository.findByCabinClass(cabinClass);
    }

    @Override
    public Cabin_Seats getSeatBySeatNumber(String seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    @Override
    public List<String> getAvailableSeatsByFlightIdAndClass(long flightId, int cabinClass) {
        List<Cabin_Seats> availableSeatsByFlightIdAndClass = seatRepository.findAvailableSeatsByFlightIdAndClass(flightId, cabinClass);
        //应该只有一条数据
        if(availableSeatsByFlightIdAndClass.isEmpty()){
            throw new RuntimeException("No seats found for flightId: " + flightId);
        }else if(availableSeatsByFlightIdAndClass.size() > 1){
            throw new RuntimeException("More than one seats found for flightId: " + flightId);
        }
        List<String> ret = new LinkedList<>();

        // 转为List<Seat>
        availableSeatsByFlightIdAndClass.get(0).getSeats().forEach(seat -> {
            ret.add(seat.getSeatNumber());
        });
        return ret;

    }

    @Override
    public boolean updateSeatBookingStatus(long flightId, long cabinId, String seatNumber, boolean isBooked) {
        return seatRepository.updateSeatBookingStatus(flightId, cabinId, seatNumber, isBooked);
    }


}
