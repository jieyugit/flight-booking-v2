package top.johnnycse.flight.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.mapper.OrderMapper;
import top.johnnycse.flight.mapper.TicketMapper;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.pojo.order;
import top.johnnycse.flight.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public List<order> find(Integer userId) {
        List<order> byUserId = orderMapper.findByUserId(userId);
        for(order i : byUserId){
            Integer flightId = i.getFlight_id();
            Flight byIdU = ticketMapper.findByIdU(flightId);
            i.setFlight(byIdU);
        }
        return byUserId;
    }
}
