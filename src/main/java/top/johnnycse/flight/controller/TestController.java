package top.johnnycse.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;


@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired


    @GetMapping("/seat")
    public Map<String,Object> seat(){
        return null;
    }
}
