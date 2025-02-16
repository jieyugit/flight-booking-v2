package top.johnnycse.flight.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenByRedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private final static Logger log = LoggerFactory.getLogger(TokenByRedisUtil.class);

    public String setToken(String userId){
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(userId, token, 30, TimeUnit.MINUTES);
        log.info("set的token"+token);
        return token;
    }

    public String delToken(String userId){
        Boolean isDelToken = redisTemplate.delete(userId);
        if(isDelToken){
            return "success";
        }else{
            return "failed";
        }

    }

    public String getToken(String userId){
        String token = redisTemplate.opsForValue().get(userId);
        if(token == null){
            return "Token已过期";
        }else{
            return token;
        }

    }

    public Map<String,Object> searchDatas(String userId,String token){
        Map<String,Object> returnMap = new HashMap();
        System.out.println("进入方法内的UserId"+userId);
        System.out.println("传入的Token"+token);
        String tokenInMemory = redisTemplate.opsForValue().get(userId);
        System.out.println("得到的TokenInMemory"+tokenInMemory);
        try{
            if(tokenInMemory == null){
                returnMap.put("code", 201);
                returnMap.put("msg", "Token过期,请重新获取");
                // return returnMap;
            }else{
                if(tokenInMemory.equals(token)){
                    returnMap.put("code", 200);
                    returnMap.put("token", token);
                    returnMap.put("msg", "请求成功");
                }else{
                    returnMap.put("code", 202);
                    returnMap.put("msg", "Token无效,请传入正确的数据");
                }
            }
            return returnMap;

        }catch(Exception e){
            e.printStackTrace();
        }
        returnMap.put("code", 400);
        returnMap.put("msg", "参数异常,请检查参数");
        return returnMap;
    }

}

