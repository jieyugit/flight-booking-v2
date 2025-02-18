package top.johnnycse.flight.service.Flight;

import org.springframework.stereotype.Service;
import top.johnnycse.flight.dto.response.FlightDTO;
import top.johnnycse.flight.pojo.Flight;

import java.util.List;

public interface FlightService {
    boolean addFlight(FlightDTO flightDTO);
    boolean deleteFlight(Long id);
    Flight updateFlight(Long id, FlightDTO flightDTO);
    List<Flight> getFlights(int page, int size);
    Flight getFlightById(Long id);
}

