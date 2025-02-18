package top.johnnycse.flight.service.Flight.TransitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.enums.FlightClass;
import top.johnnycse.flight.pojo.Cabin;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.repository.CabinRepository;
import top.johnnycse.flight.repository.FlightRepository;
import top.johnnycse.flight.service.Flight.FlightService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransitService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CabinRepository cabinRepository;
    @Autowired
    private FlightService flightService;


    /**
     * 计算当天的中转方案
     */
    public List<TransitRoute> findTransitRoutes(String departure, String arrival, LocalDate departureDate, int maxTransit) {
        List<Flight> allFlights = flightService.findFlightsOnDate(departureDate);

        // 构建航班图，机场为节点，航班为边
        Map<String, List<Flight>> flightMap = new HashMap<>();
        for (Flight flight : allFlights) {
            flightMap.computeIfAbsent(flight.getDepartureAirport(), k -> new ArrayList<>()).add(flight);
        }

        // BFS
        Queue<TransitRoute> queue = new LinkedList<>();
        queue.add(new TransitRoute(Collections.emptyList(), 0, 0, 0));

        List<TransitRoute> validRoutes = new ArrayList<>();

        while (!queue.isEmpty()) {
            TransitRoute route = queue.poll();
            List<Flight> currentPath = route.getFlights();
            String lastAirport = currentPath.isEmpty() ? departure : currentPath.get(currentPath.size() - 1).getArrivalAirport();

            // 如果已经到达目标机场记录
            if (lastAirport.equals(arrival)) {
                validRoutes.add(route);
                continue;
            }

            // 限制中转次数
            if (route.getTransitCount() >= maxTransit) continue;

            // 遍历当前机场的所有可选航班
            for (Flight nextFlight : flightMap.getOrDefault(lastAirport, Collections.emptyList())) {
                // 确保中转时间足够
                if (!currentPath.isEmpty()) {
                    Flight lastFlight = currentPath.get(currentPath.size() - 1);
                    if (Duration.between(lastFlight.getArrivalTime(), nextFlight.getDepartureTime()).toMinutes() < 60) {
                        continue;
                    }
                }

                // 计算新的总时长、价格
                long newDuration = route.getTotalDuration() +
                        Duration.between(nextFlight.getDepartureTime(), nextFlight.getArrivalTime()).toMinutes();

                double newPrice = route.getTotalPrice() + flightService.getPriceForFlightAndClass(nextFlight, FlightClass.ECONOMY);

                // 生成新的中转路线
                List<Flight> newPath = new ArrayList<>(currentPath);
                newPath.add(nextFlight);
                queue.add(new TransitRoute(newPath, newPrice, newDuration, route.getTransitCount() + 1));
            }
        }

        return validRoutes;
    }
}


