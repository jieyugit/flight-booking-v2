package top.johnnycse.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.johnnycse.flight.pojo.order;
import top.johnnycse.flight.service.OrderService;
import top.johnnycse.flight.service.UserService;
import top.johnnycse.flight.utils.api;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderServer;

    @PostMapping("/find")
    List<order> find(HttpServletRequest request){
        try{
            String name = (String)userService.getUserNameFromToken(request).get("result");
            Integer userID = userService.getUserByName(name);
            return orderServer.find(userID);
        }catch (NullPointerException e){
            return null;
        }
    }
}
