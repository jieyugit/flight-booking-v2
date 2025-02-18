package top.johnnycse.flight.service.Flight.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.dto.response.FlightDTO;
import top.johnnycse.flight.enums.FlightStatus;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.repository.FlightRepository;
import top.johnnycse.flight.service.Flight.FlightService;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public boolean addFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setAirlineCode(flightDTO.getAirlineCode());
        flight.setDepartureAirport(flightDTO.getDepartureAirport());
        flight.setArrivalAirport(flightDTO.getArrivalAirport());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setAircraftModel(flightDTO.getAircraftModel());
        flight.setTotalSeats(flightDTO.getTotalSeats());
        flight.setStatus(FlightStatus.valueOf(flightDTO.getStatus().toUpperCase()));
        int row =  flightRepository.insertFlight(flight);
        return row > 1;
    }

    @Override
    @CacheEvict(value = "flight", key = "'flight_id_'+#id")
    public boolean deleteFlight(Long id) {
        int row = flightRepository.deleteFlight(id);
        return row > 1;
    }

    @Override
    @CachePut(value = "flight", key = "'flight_id_'+#id")
    public Flight updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setAirlineCode(flightDTO.getAirlineCode());
        flight.setDepartureAirport(flightDTO.getDepartureAirport());
        flight.setArrivalAirport(flightDTO.getArrivalAirport());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setAircraftModel(flightDTO.getAircraftModel());
        flight.setTotalSeats(flightDTO.getTotalSeats());
        flight.setStatus(FlightStatus.valueOf(flightDTO.getStatus().toUpperCase()));
        flight.setFlightId(id);
        int row = flightRepository.updateFlight(flight);
        return row > 1 ? flight : null;

    }

    @Override
    public List<Flight> getFlights(int page, int size) {
        return flightRepository.getFlights((page - 1) * size, size);
    }

    @Override
    @Cacheable(value = "flight", key = "'flight_id_'+#id")
    public Flight getFlightById(Long id) {
        return flightRepository.getFlightById(id);
    }


}
