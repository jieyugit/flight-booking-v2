package top.johnnycse.flight.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.johnnycse.flight.mapper.CityMapper;
import top.johnnycse.flight.service.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<String> getDepartCity() {
        return cityMapper.departCity();
    }

    @Override
    public List<String> getDestnCity() {
        return cityMapper.destnCity();
    }
}
