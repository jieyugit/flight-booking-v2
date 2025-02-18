package top.johnnycse.flight.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO {

    private String flightNumber;  // 航班号
    private String airlineCode;   // 航空公司代码
    private String departureAirport;  // 出发机场
    private String arrivalAirport;  // 到达机场
    private LocalDateTime departureTime;  // 出发时间
    private LocalDateTime arrivalTime;  // 到达时间
    private String aircraftModel;  // 飞机型号
    private int totalSeats;  // 总座位数
    private String status;  // 航班状态（计划中、已起飞、已取消）
}
