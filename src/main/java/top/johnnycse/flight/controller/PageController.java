package top.johnnycse.flight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping(value = {"","/index","index.html"})
    public String index(){
        return "/index.html";
    }

    @GetMapping(value = {"/pagetest","detailtest.html"})
    public String detailtest(){
        return "/detailtest.html";
    }

    @GetMapping("/ticketList.html")
    public String ticketList(){
        return "/ticketList.html";
    }

    @GetMapping("/details.html")
    public String details(){
        return "/details.html";
    }

    @GetMapping("/login.html")
    public String login(){
        return "/login.html";
    }

    @GetMapping("/register.html")
    public String register(){
        return "/register.html";
    }

    @GetMapping("/thankyou.html")
    public String thank(){
        return "/thankyou.html";
    }

    @GetMapping("/order.html")
    public String order(){
        return "/order.html";
    }


    @GetMapping("/seat.html")
    public String seat(){
        return "/seatSelect.html";
    }

}
