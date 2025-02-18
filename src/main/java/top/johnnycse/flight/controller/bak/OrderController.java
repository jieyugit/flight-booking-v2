package top.johnnycse.flight.controller.bak;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private OrderService orderServer;
//
//    @PostMapping("/find")
//    List<order> find(HttpServletRequest request){
//        try{
//            String name = (String)userService.getUserNameFromToken(request).get("result");
//            Integer userID = userService.getUserByName(name);
//            return orderServer.find(userID);
//        }catch (NullPointerException e){
//            return null;
//        }
//    }
}
