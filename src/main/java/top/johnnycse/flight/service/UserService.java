package top.johnnycse.flight.service;

import top.johnnycse.flight.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    public User getUserById(Integer Id);
    public Map<String, Object> getUserNameFromToken(HttpServletRequest request);

    Integer getUserByName(String userName);
}
