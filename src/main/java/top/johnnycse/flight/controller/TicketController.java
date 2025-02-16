package top.johnnycse.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.johnnycse.flight.pojo.Detail;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.service.TicketService;
import top.johnnycse.flight.utils.api;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/page")
    public Map<String,Object> paged(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam String origin,
                                    @RequestParam String destination,
                                    @RequestParam String depart){
        return ticketService.paged(pageNum,pageSize,origin,destination,depart);

    }

    @PostMapping("/submit")
    public Map<String,Object> submit(@RequestParam String origin,
                                     @RequestParam String destination,
                                     @RequestParam String depart_date,
                                     @RequestParam Integer adults,
                                     @RequestParam(required = false,defaultValue = "0") Integer children,
                                     @RequestParam String travel_class){

        Integer total = Integer.sum(adults,children);//总的需要的票数
        depart_date = depart_date+" 00:00:00";//格式化时间

        return api.success(ticketService.leftTicket(origin,destination,depart_date,total,travel_class));


    }

    @PostMapping("/findPage")
    public Map<String,Object> findPage(@RequestParam String origin,
                                     @RequestParam String destination,
                                     @RequestParam String depart_date,
                                     @RequestParam Integer adults,
                                     @RequestParam(required = false,defaultValue = "0") Integer children,
                                     @RequestParam String travel_class,
                                     @RequestParam Integer page){
        //查询余票
        Integer total = Integer.sum(adults,children);
        depart_date = depart_date+" 00:00:00";

        return api.success(ticketService.findFlightByPage(origin,destination,depart_date,total,travel_class,page));


    }

    @GetMapping("/submit")
    public Map<String,Object> submitGet(@RequestParam String origin,
                                     @RequestParam String destination,
                                     @RequestParam String depart_date,
                                     @RequestParam Integer adults,
                                     @RequestParam(required = false,defaultValue = "0") Integer children,
                                     @RequestParam String travel_class){
        //查询余票
        Integer total = Integer.sum(adults,children);
        depart_date = depart_date+" 00:00:00";

        return api.success(ticketService.leftTicket(origin,destination,depart_date,total,travel_class));


    }

    @GetMapping("/findById/{id}")
    public List<Flight> findById(@PathVariable("id") Integer flightId){
        return ticketService.findById(flightId);
    }

    // 处理提交的信息
    @PostMapping("/info/{adult}/{child}/{flightId}")
    public Map<String,Object> info(@RequestParam Map<String, String> params,
                                   @PathVariable("adult") Integer adults,
                                   @PathVariable("child") Integer children,
                                   @PathVariable("flightId") Integer flightId,
                                   HttpServletRequest request){
        //System.out.println("adults = "+adults+" children = "+children);
        //new map
        LinkedList<Detail> details = new LinkedList<Detail>();
        if(adults!=null){
              for(int i = 0;i<adults;i++){
                  Detail detail = new Detail();
                  detail.setName(params.get("Aname"+i));
                  detail.setAge(params.get("AAge"+i));
                  detail.setPhone_number(params.get("phoneNumber"));
                  detail.setEmail(params.get("email"));
                  detail.setAddress(params.get("address"));
                  detail.setFlight_id(flightId);
                  details.add(detail);
              }
        }

        if(children!=null){
            for(int i = 0 ;i< children;i++){
                Detail detail = new Detail();
                detail.setName(params.get("Cname"+i));
                detail.setAge(params.get("CAge"+i));
                detail.setPhone_number(params.get("phoneNumber"));
                detail.setEmail(params.get("email"));
                detail.setAddress(params.get("address"));
                detail.setFlight_id(flightId);
                details.add(detail);
            }
        }
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            String paramName = entry.getKey();
//            String paramValue = entry.getValue();
//            // 处理参数键值对
//            System.out.println(paramName + " = " + paramValue);
//        }

        return ticketService.setDetails(details,request);
    }

}
