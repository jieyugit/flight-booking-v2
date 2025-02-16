package top.johnnycse.flight.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {
    @Select("select DISTINCT depart from flight;")
    List<String> departCity();

    @Select("select DISTINCT destn from flight;")
    List<String> destnCity();
}
