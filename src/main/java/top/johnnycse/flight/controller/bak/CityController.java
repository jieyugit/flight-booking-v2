package top.johnnycse.flight.controller.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/city")
public class CityController {
//    @Autowired
//    CityService cityService;
//
//
//    @GetMapping("/depart")
//    public List<String> depart(){
//        return cityService.getDepartCity();
//    }
//
//    @GetMapping("/destin")
//    public List<String> destin(){
//        return cityService.getDestnCity();
//    }
}
