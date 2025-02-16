package top.johnnycse.flight.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface LoginService {
    public Map<String,Object> login(String userName,String passwd,HttpServletResponse response);
    public Map<String,Object> loginOut(HttpServletRequest request);
    public Map<String,Object> signUp(String userName,String passwd);
}
