package top.johnnycse.flight.service.Flight;

public interface CabinService {
    //座位余量减扣，防止超卖
    boolean deductCabin(Long flightId, Integer count);

}
