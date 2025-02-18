package top.johnnycse.flight.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.johnnycse.flight.pojo.Cabin;

import java.util.List;

@Mapper
public interface CabinRepository {
    @Select("SELECT * FROM cabin WHERE flight_id = #{flightId}")
    @Results({
            @Result(property = "cabinId", column = "cabin_id"),
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "cabinClass", column = "cabin_class"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "availableSeats", column = "available_seats")
    })
    List<Cabin> findCabinsByFlightId(Long flightId);
}
