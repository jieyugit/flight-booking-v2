package top.johnnycse.flight.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.johnnycse.flight.pojo.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FlightRepository {
    // 插入航班数据
    @Insert("INSERT INTO flight (flight_number, airline_code, departure_airport, arrival_airport, " +
            "departure_time, arrival_time, aircraft_model, total_seats, status) " +
            "VALUES (#{flightNumber}, #{airlineCode}, #{departureAirport}, #{arrivalAirport}, " +
            "#{departureTime}, #{arrivalTime}, #{aircraftModel}, #{totalSeats}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "flightId")
    Integer insertFlight(Flight flight);

    // 根据 ID 删除航班
    @Delete("DELETE FROM flight WHERE flight_id = #{id}")
    Integer deleteFlight(Long id);

    // 更新航班
    @Update("UPDATE flight SET flight_number = #{flightNumber}, airline_code = #{airlineCode}, " +
            "departure_airport = #{departureAirport}, arrival_airport = #{arrivalAirport}, " +
            "departure_time = #{departureTime}, arrival_time = #{arrivalTime}, " +
            "aircraft_model = #{aircraftModel}, total_seats = #{totalSeats}, status = #{status} " +
            "WHERE flight_id = #{flightId}")
    Integer updateFlight(Flight flight);

    // 根据 ID 查询航班
    @Select("SELECT * FROM flight WHERE flight_id = #{id}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    Flight getFlightById(Long id);

    // 查询所有航班（带分页）
    @Select("SELECT * FROM flight LIMIT #{offset}, #{size}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlights(@Param("offset") int offset, @Param("size") int size);

    // 根据航班号查询航班
    @Select("SELECT * FROM flight WHERE flight_number = #{flightNumber}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlightByFlightNumber(String flightNumber);

    // 根据起飞日期查询航班
    @Select("SELECT * FROM flight WHERE departure_time = #{departureTime}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlightByDepartureTime(String departureTime);

    //根据起飞机场查询航班
    @Select("SELECT * FROM flight WHERE departure_airport = #{departureAirport}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlightByDepartureAirport(String departureAirport);

    //根据到达机场查询航班
    @Select("SELECT * FROM flight WHERE arrival_airport = #{arrivalAirport}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlightByArrivalAirport(String arrivalAirport);

    //根据航班号和起飞日期查询航班
    @Select("SELECT * FROM flight WHERE flight_number = #{flightNumber} AND departure_time = #{departureTime}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlightByFlightNumberAndDepartureTime(String flightNumber, String departureTime);

    //根据起飞日期和起飞机场和目的机场查询航班
    @Select("SELECT * FROM flight WHERE DATE(departure_time) = #{departureDate} AND departure_airport = #{departureAirport} AND arrival_airport = #{arrivalAirport}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> getFlightByDepartureTimeAndDepartureAirportAndArrivalAirport(String departureTime, String departureAirport, String arrivalAirport);

    //根据日期范围查询飞机
    @Select("SELECT * FROM flight WHere DATE(departure_time) = #{departureDate}")
    @Results({
            @Result(property = "flightId", column = "flight_id"),
            @Result(property = "flightNumber", column = "flight_number"),
            @Result(property = "airlineCode", column = "airline_code"),
            @Result(property = "departureAirport", column = "departure_airport"),
            @Result(property = "arrivalAirport", column = "arrival_airport"),
            @Result(property = "departureTime", column = "departure_time"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "aircraftModel", column = "aircraft_model"),
            @Result(property = "totalSeats", column = "total_seats"),
            @Result(property = "status", column = "status")
    })
    List<Flight> findFlightsByDate(LocalDate departureDate);



}
