package top.johnnycse.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.johnnycse.flight.dto.response.FlightDTO;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.service.Flight.FlightService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    //TODO: 需要鉴权
    @PostMapping("/add")
    public ResponseEntity<Boolean> addFlight(@RequestBody @Valid FlightDTO flightDTO) {
        return ResponseEntity.ok(flightService.addFlight(flightDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody @Valid FlightDTO flightDTO) {
        return ResponseEntity.ok(flightService.updateFlight(id, flightDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Flight>> getFlights(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(flightService.getFlights(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

}
