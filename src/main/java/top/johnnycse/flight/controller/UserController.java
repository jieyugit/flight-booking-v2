package top.johnnycse.flight.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.johnnycse.flight.service.LoginService;
import top.johnnycse.flight.service.RegisterService;
import top.johnnycse.flight.service.UserService;
import top.johnnycse.flight.utils.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    Map<String,Object> login(@RequestParam String username,
                             @RequestParam String password,
                             HttpServletResponse response,
                             HttpServletRequest request){

        Map<String, Object> ret = loginService.login(username, password,response);

        if(ret.get("message")=="ok"){
            Cookie cookie = new Cookie("cookie",(String) ret.get("cookie"));
            ret.remove("cookie");//删除一下ret带的Cookie 如果要调试可以注释掉这个
            cookie.setMaxAge(60 * 60 * 24);
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setContentType("application/json;charset=UTF-8");
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return ret;

    }


    @PostMapping("/register")
    Map<String,Object> register(@RequestParam String username,
                                @RequestParam String password){
        if(registerService.register(username,password)){
            return api.success("./login.html");
        }else{
            return api.error("注册失败，请联系管理员");
        }

    }

    @PostMapping("/getUserName")
    public Map<String,Object> getUserName(HttpServletRequest request){
        return userService.getUserNameFromToken(request);
    }

    @GetMapping("/loginOut")
    public Map<String,Object> loginOut(HttpServletRequest request){
        return loginService.loginOut(request);
    }
}
