package top.johnnycse.flight.service.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.johnnycse.flight.dto.request.RegisterRequest;
import top.johnnycse.flight.dto.response.UserLoginResponse;
import top.johnnycse.flight.pojo.User;
import top.johnnycse.flight.utils.SnowFlakeUtil;

public interface UserService extends UserDetailsService {

    public UserLoginResponse login(String username, String password);

    public User loadUserById(Long userId);

    public void register(RegisterRequest request);

}
