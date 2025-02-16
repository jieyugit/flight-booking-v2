package top.johnnycse.flight.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.mapper.UserMapper;
import top.johnnycse.flight.pojo.User;
import top.johnnycse.flight.service.UserService;
import top.johnnycse.flight.utils.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User getUserById(Integer Id) {
        if (Id == null)
            return null;
        return userMapper.getUserInfoById(Id);
    }

    @Override
    public Map<String, Object> getUserNameFromToken(HttpServletRequest request) {
        String token =null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("cookie")){
                    token = cookie.getValue();
                }
            }
        }
        if(token!=null){
            String name = redisTemplate.opsForValue().get(token);
            if(name==null){
                return api.error("未登录");
            }else{
                return api.success(name);
            }
        }else return api.error("未登录");
    }

    @Override
    public Integer getUserByName(String userName) {
        return userMapper.getUserIdByUserName(userName);
    }


}
