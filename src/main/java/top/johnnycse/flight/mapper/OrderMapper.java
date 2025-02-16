package top.johnnycse.flight.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.johnnycse.flight.pojo.order;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from orderinfo where user_id = #{userId}")
    List<order> findByUserId(Integer userId);


}
