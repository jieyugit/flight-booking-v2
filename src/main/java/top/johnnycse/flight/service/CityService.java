package top.johnnycse.flight.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CityService {
    List<String> getDepartCity();
    List<String> getDestnCity();
}
