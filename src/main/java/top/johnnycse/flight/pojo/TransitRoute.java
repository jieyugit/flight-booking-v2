package top.johnnycse.flight.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import top.johnnycse.flight.pojo.Flight;

import java.util.List;

@Data
public class TransitRoute {
    private List<Flight> flights;
    private double totalPrice;
    private long totalDuration;
    private int transitCount;

    public TransitRoute(List<Flight> flights, double totalPrice, long totalDuration, int transitCount) {
        this.flights = flights;
        this.totalPrice = totalPrice;
        this.totalDuration = totalDuration;
        this.transitCount = transitCount;
    }
}
