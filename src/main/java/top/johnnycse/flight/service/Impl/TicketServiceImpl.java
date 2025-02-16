package top.johnnycse.flight.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.mapper.TicketMapper;
import top.johnnycse.flight.pojo.Detail;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.service.SeatService;
import top.johnnycse.flight.service.TicketService;
import top.johnnycse.flight.service.UserService;
import top.johnnycse.flight.utils.api;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    SeatService seatService;
    @Autowired
    TicketMapper ticketmapper;
    @Autowired
    UserService userService;

    //Economy class Business class First class

    @Override
    public List<Flight> leftTicket(String depart, String destn, String time, Integer need, String travel_class) {
        List<Flight> flight = ticketmapper.findFlight(depart, destn, time);
        for(Flight f:flight){
            String flightId = f.getId();
            Integer integer = seatService.findInteger(flightId);
            f.setTicketLeft(integer);
        }

        return flight;

    }

    @Override
    public List<Flight> findFlightByPage(String depart, String destn, String time, Integer need, String travel_class, Integer page) {
        List<Flight> flight = ticketmapper.findFlightByPage(depart, destn, time,(page-1)*5,page*5);
        for(Flight f:flight){
            String flightId = f.getId();
            Integer integer = seatService.findInteger(flightId);
            f.setTicketLeft(integer);
        }

        return flight;
    }

    @Override
    public boolean findLeft(String flightId, Integer need) {
        Integer integer = seatService.findInteger(flightId);
        return integer >= need;
    }

    @Override
    public List<Flight> findById(Integer flightId) {
        return ticketmapper.findById(flightId);
    }

    @Override
    public Map<String, Object> setDetails(LinkedList<Detail> details, HttpServletRequest request) {
//        for(Detail i : details){
//            ticketmapper.insertDetail(i);
//        }
        System.out.println(details);

        String userName = (String)userService.getUserNameFromToken(request).get("result");
        Integer userId = userService.getUserByName(userName);
        for(Detail i : details){
            i.setUser_id(userId);
            ticketmapper.insertDetail(i);
        }




        return api.success("success");
    }

    @Override
    public Map<String, Object> paged(Integer pageNum, Integer pageSize, String origin, String destination, String depart) {
        //查询一共有多少条
        Integer total = ticketmapper.findTotal(origin,destination,depart);

        //返回需要的条数
        List<Flight> flight = ticketmapper.findFlightByPage(origin,destination,depart,(pageNum-1)*pageSize,pageSize);

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("total",total);
        objectObjectHashMap.put("data",flight);
        return objectObjectHashMap;
    }
}
