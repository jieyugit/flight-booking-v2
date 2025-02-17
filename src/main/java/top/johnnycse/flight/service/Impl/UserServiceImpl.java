package top.johnnycse.flight.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.dto.request.RegisterRequest;
import top.johnnycse.flight.dto.response.UserLoginResponse;
import top.johnnycse.flight.pojo.User;
import top.johnnycse.flight.repository.userRepository;
import top.johnnycse.flight.service.UserService;
import top.johnnycse.flight.utils.JwtUtil;
import top.johnnycse.flight.utils.SnowFlakeUtil;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private userRepository userRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private SnowFlakeUtil snowFlakeUtil;


    @Override
    public UserLoginResponse login(String username, String password) {

            User user = userRepository.findByUsername(username);

            if(user==null) throw new UsernameNotFoundException("用户名不存在");


            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }

            // 生成 JWT 并返回
            String token = jwtUtil.generateToken(user.getUserId());
            return new UserLoginResponse(user.getUserId(), user.getUsername(), token);


    }

    @Override
    public User loadUserById(Long userId) {
        if(userId == null) {
            throw new RuntimeException("用户 ID 不能为空");
        }
        return (User) userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户不存在"));
    }

    @Override
    public void register(RegisterRequest request) {
        // 检查用户名是否已存在
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查手机号是否已存在
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("手机号已存在");
        }

        // 创建用户
        User user = new User();
        user.setUserId(snowFlakeUtil.getNextId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // 加密密码
        user.setPhone(request.getPhone());
        user.setUserType(0); // 默认普通用户

        // 保存用户
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new RuntimeException("用户名不能为空");
        }
        User user = userRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("用户不存在");
        return user;
    }
}
