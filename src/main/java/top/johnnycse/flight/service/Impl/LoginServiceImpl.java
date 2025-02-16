package top.johnnycse.flight.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.mapper.UserMapper;
import top.johnnycse.flight.pojo.User;
import top.johnnycse.flight.service.LoginService;
import top.johnnycse.flight.utils.TokenByRedisUtil;
import top.johnnycse.flight.utils.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TokenByRedisUtil tokenByRedisUtil;

    @Override
    public Map<String, Object> login(String userName, String passwd,HttpServletResponse response) {
        //从数据库中查找userName
        User user = userMapper.getUserInfoByUserName(userName);

        //用户不存在
        if(user == null) return api.error("用户不存在");
        //用户状态判断
        if(user.getDeleted()==1) return api.error("用户不存在");
        if(user.getStatus()==0) return api.error("用户状态异常，请联系管理员");

        //获取密码
        String password = user.getPassword();

        if(passwd.toUpperCase().equals(password)) {
            String token = tokenByRedisUtil.setToken(userName);
            response.setHeader("authorization",token);
            redisTemplate.opsForValue().set(token,user.getUserName(), Duration.ofMinutes(30L));
            return api.successCookie("./index.html",token);
        }
        else return api.error("密码错误");
    }

    @Override
    public Map<String, Object> loginOut(HttpServletRequest request) {
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
            String s = redisTemplate.opsForValue().get(token);
            if(s!=null){
                redisTemplate.delete(s);
            }
            redisTemplate.delete(token);

            return api.success("登出成功");
        }else return api.error("失败");
    }

    @Override
    public Map<String, Object> signUp(String userName, String passwd) {
        return null;
    }

}
