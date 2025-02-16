package top.johnnycse.flight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.johnnycse.flight.pojo.Detail;
import top.johnnycse.flight.pojo.Flight;

import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    @Select("select * from flight where depart=#{depart} and destn=#{destn} and depart_Time>=#{time}")
    List<Flight> findFlight(String depart, String destn, String time);

    //分页查询
    @Select("select * from flight where depart=#{depart} and destn=#{destn} and depart_Time>=#{time} limit #{left},#{right}")
    List<Flight> findFlightByPage(String depart, String destn, String time,Integer left,Integer right);

    @Select("select * from flight where id = #{flightId}")
    List<Flight> findById(Integer flightId);

    @Select("select * from flight where id = #{flightId}")
    Flight findByIdU(Integer flightId);

    @Insert("insert into orderinfo (name,age,phone_number,email,address,user_id,flight_id) VALUES(#{name},#{age},#{phone_number},#{email},#{address},#{user_id},#{flight_id})")
    void insertDetail(Detail i);

    @Select("select count(*) from flight where depart=#{origin} and destn=#{destination} and depart_Time >=#{depart}")
    Integer findTotal(String origin, String destination, String depart);
}
