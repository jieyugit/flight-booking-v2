package top.johnnycse.flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.johnnycse.flight.pojo.Flight;
import top.johnnycse.flight.repository.FlightRepository;
import top.johnnycse.flight.service.Flight.TransitService.TransitRoute;
import top.johnnycse.flight.service.Flight.TransitService.TransitService;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightServiceTest {
    @Autowired
    private TransitService transitService;

    @Autowired
    private FlightRepository flightRepository;

//    @Test
    public void testFindTransitRoutes() {
        // 设定测试日期
        LocalDate departureDate = LocalDate.parse("2024-04-11");

        // 从数据库查询航班数据
        List<Flight> allFlights = flightRepository.findFlightsByDate(departureDate);

        // 断言
        assertFalse(allFlights.isEmpty(), "航班数据应该存在");

        String departureAirport = "成都天府国际机场";
        String arrivalAirport = "大兴国际机场";
        int maxTransit = 3;

        List<TransitRoute> routes = transitService.findTransitRoutes(departureAirport, arrivalAirport, departureDate ,maxTransit);

        // 验证结果
        assertNotNull(routes, "中转路线结果不应为空");
        assertTrue(routes.size() > 0, "应该找到至少一条中转路线");

        // 进一步验证路径的价格、时长
        TransitRoute route = routes.get(0);
        assertEquals(2, route.getFlights().size(), "中转路线应包含两个航班");
        assertTrue(route.getTotalPrice() > 0, "总价格应大于 0");
        assertTrue(route.getTotalDuration() > 0, "总时长应大于 0");
    }
}
