package top.johnnycse.flight.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.johnnycse.flight.pojo.Seat;
import top.johnnycse.flight.service.SeatService;
import top.johnnycse.flight.utils.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class SeatServiceImpl implements SeatService {


    @Override
    public Map<String,Object> seatAvailable(Integer flightId) {

        return null;

    }

    @Override
    public boolean insertSeat(String flightId) {
        return true;
    }

    @Override
    public String findNumber(String flightId) {
        return null;
    }

    public Integer findInteger(String flightId){
        return null;


    }






}
