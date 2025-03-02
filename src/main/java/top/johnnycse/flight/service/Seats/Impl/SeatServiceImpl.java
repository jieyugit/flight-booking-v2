package top.johnnycse.flight.service.Seats.Impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;
import top.johnnycse.flight.pojo.Cabin_Seats;
import top.johnnycse.flight.pojo.Seat;
import top.johnnycse.flight.repository.SeatRepository;
import top.johnnycse.flight.service.Seats.SeatService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService, ApplicationContextAware {
    @Autowired
    private SeatRepository seatRepository;

    private ApplicationContext applicationContext;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    //@Cacheable(value = "seats", key = "'seats_'+#flightId") 测试之后不用缓存
    public Cabin_Seats getSeatsByFlightId(long flightId) {
        Optional<Cabin_Seats> cabinSeats = seatRepository.findByFlightId(flightId);

        if (cabinSeats.isPresent()) {
            return cabinSeats.get();
        } else {
            throw new RuntimeException("No seats found for flightId: " + flightId);
        }
    }

    @Override
    public Cabin_Seats getSeatBySeatNumber(long flightId, String seatNumber) {
        Optional<Cabin_Seats> results = seatRepository.findByFlightId(flightId);
        if (!results.isPresent()) {
            throw new RuntimeException("No seats found for flightId: " + flightId);
        }
        results.get().getSeats().removeIf(result -> !result.getSeatNumber().equals(seatNumber));
        if (results.get().getSeats().isEmpty()) {
            throw new RuntimeException("No seat found for seatNumber: " + seatNumber);
        }
        return results.get();
    }

    @Override
    public List<String> getAvailableSeatsByFlightIdAndClass(long flightId, int cabinClass) {
        Optional<Cabin_Seats> byFlightId = seatRepository.findByFlightId(flightId);
        if (!byFlightId.isPresent()) {
            throw new RuntimeException("No seats found for flightId: " + flightId);
        }
        List<Seat> results = byFlightId.get().getSeats();
        results.removeIf(result -> result.getCabinClass() != cabinClass || result.isBooked());

        List<String> ret = new LinkedList<>();
        for (Seat seat : results) {
            ret.add(seat.getSeatNumber());
        }

        return ret;
    }


    @Override
    public boolean updateSeatBookingStatus(long flightId, long cabinId, String seatNumber ,boolean isBooked) {
        Query query = new Query(Criteria.where("flight_id").is(flightId)
                .and("seats.cabin_id").is(cabinId)
                .and("seats.seat_number").is(seatNumber)
                .and("seats.is_booked").is(false));
        Update update = new Update().set("seats.$.is_booked", isBooked);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true); //原子操作，保证只有一个线程能够修改数据
        Cabin_Seats updatedSeat = mongoTemplate.findAndModify(query, update, options, Cabin_Seats.class);
        if (updatedSeat == null) {
            throw new RuntimeException("Seat already booked by another user.");
        }
        return true;
    }

    //解决@Cacheable注解时同一个类中调用缓存方法不生效
    // 需要调用其他缓存方法时需要applicationContext.getBean(xxxx.class);再调用方法
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
