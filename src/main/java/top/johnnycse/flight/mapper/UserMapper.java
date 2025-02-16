package top.johnnycse.flight.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.johnnycse.flight.pojo.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserInfoById(Integer id);

    @Select("select * from user where userName  = #{UserName}")
    User getUserInfoByUserName(String UserName);
    @Select("select id from user where userName  = #{UserName}")
    Integer getUserIdByUserName(String UserName);

    @Insert("insert into user (userName,password) VALUES (#{userName},#{password})")
    Integer insertUser(String userName,String password);



}
