package top.johnnycse.flight.service;

import top.johnnycse.flight.pojo.order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<order> find(Integer userId);
}
