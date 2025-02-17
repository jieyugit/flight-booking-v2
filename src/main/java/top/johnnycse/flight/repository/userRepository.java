package top.johnnycse.flight.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.johnnycse.flight.pojo.User;

import java.util.Optional;

@Repository
public interface userRepository {
    @Select("select * from user where username = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "userType", column = "user_type"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    User findByUsername(String username);

    @Select("select * from user where id = #{userId}")
    Optional<User> findById(Long userId);

    @Select("SELECT COUNT(*) > 0 FROM user WHERE username = #{username}")
    boolean existsByUsername(String username);

    @Insert("INSERT INTO user (user_id,username, password, phone, user_type, create_time, update_time) " +
            "VALUES (#{userId},#{username}, #{password}, #{phone}, #{userType}, NOW(), NOW())")
    void save(User user);

    @Select("SELECT COUNT(*) > 0 FROM user WHERE phone = #{phone}")
    boolean existsByPhone(String phone);
}
