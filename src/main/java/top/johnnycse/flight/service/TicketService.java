package top.johnnycse.flight.service;

import top.johnnycse.flight.pojo.Detail;
import top.johnnycse.flight.pojo.Flight;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface TicketService {
    public List<Flight> leftTicket(String depart, String destn, String time, Integer need, String travel_class);

    public List<Flight> findFlightByPage(String depart, String destn, String time, Integer need, String travel_class,Integer page);

    public boolean findLeft(String flightId,Integer need);

    List<Flight> findById(Integer flightId);

    Map<String, Object> setDetails(LinkedList<Detail> details, HttpServletRequest request);

    Map<String, Object> paged(Integer pageNum, Integer pageSize, String origin, String destination, String depart);
}
