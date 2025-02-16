package top.johnnycse.flight.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class api {
    public static Map<String,Object> success(Object message){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",200);
        HashMap.put("message","ok");
        HashMap.put("result",message);
        HashMap.put("type","success");
        return HashMap;
    }

    public static Map<String,Object> successCookie(Object message,String Cookie){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",200);
        HashMap.put("message","ok");
        HashMap.put("result",message);
        HashMap.put("type","success");
        HashMap.put("cookie",Cookie);
        return HashMap;
    }

    public static Map<String,Object> successSetToken(String token){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",200);
        HashMap.put("message","ok");
        HashMap.put("result",token);
        HashMap.put("type","success");
        return HashMap;
    }

    public static Map<String,Object> success(Map<String,Object> result){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",200);
        HashMap.put("message","ok");
        HashMap.put("result",result);
        HashMap.put("type","success");
        return HashMap;
    }

    public static <T> Map<String,Object> success(List<T> result){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",200);
        HashMap.put("message","ok");
        HashMap.put("result",result);
        HashMap.put("type","success");
        return HashMap;
    }

    public static Map<String,Object> error(){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",-1);
        HashMap.put("message","Request failed");
        HashMap.put("result",null);
        HashMap.put("type","error");
        return HashMap;
    }

    public static Map<String,Object> error(String message){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",-1);
        HashMap.put("message",message);
        HashMap.put("result",null);
        HashMap.put("type","error");
        return HashMap;
    }

    public static Map<String,Object> adminSuccess(Object message,Object data){
        Map<String, Object> HashMap = new HashMap<>();
        HashMap.put("code",0);
        HashMap.put("message",message);
        HashMap.put("data",data);
        HashMap.put("type","success");
        HashMap.put("success","success");
        return HashMap;
    }
}
