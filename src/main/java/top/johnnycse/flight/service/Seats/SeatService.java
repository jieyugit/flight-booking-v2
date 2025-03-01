package top.johnnycse.flight.service.Seats;

import top.johnnycse.flight.pojo.Cabin;
import top.johnnycse.flight.pojo.Cabin_Seats;
import top.johnnycse.flight.pojo.Seat;

import java.util.List;

public interface SeatService {
    public Cabin_Seats getSeatsByFlightId(long flightId);

    // 根据座位编号和航班号查询座位
    public Cabin_Seats getSeatBySeatNumber(long flightId,String seatNumber);

    /**
     * 根据航班号和舱位类别查询未预定的座位
     * @param flightId 航班号
     * @param cabinClass 舱位类别 (0 - 经济舱, 1 - 商务舱, 2 - 头等舱)
     * @return 未预定的座位列表
     */
    List<String> getAvailableSeatsByFlightIdAndClass(long flightId, int cabinClass);

    /**
     * 更新指定座位的预订状态
     * @param flightId 航班号
     * @param cabinId 舱位ID
     * @param seatNumber 座位号
     * @param isBooked 预订状态 (true - 已预定, false - 未预定)
     */
    boolean updateSeatBookingStatus(long flightId, long cabinId, String seatNumber,boolean isBooked);



}
