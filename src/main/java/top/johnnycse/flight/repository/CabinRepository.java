package top.johnnycse.flight.repository;

import org.apache.ibatis.annotations.*;
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

    @Select("SELECT * FROM cabin WHERE cabin_id = #{cabinId}")
    Cabin findByCabinId(@Param("cabinId") Long cabinId);

    @Update("UPDATE cabin SET remaining_seats = remaining_seats - 1, version = version + 1 " +
            "WHERE cabin_id = #{cabinId} AND version = #{version} AND remaining_seats > 0")
    int updateRemainingSeat(@Param("cabinId") Long cabinId, @Param("version") long version);
}
