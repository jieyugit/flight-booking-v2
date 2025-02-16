package top.johnnycse.flight.service;

import java.util.Map;

public interface SeatService {
    public Map<String,Object> seatAvailable(Integer flightId);

    public boolean insertSeat(String flightId);

    public String findNumber(String flightId);

    public Integer findInteger(String flightId);
}
