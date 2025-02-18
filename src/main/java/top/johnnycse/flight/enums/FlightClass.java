package top.johnnycse.flight.enums;

import lombok.Getter;

@Getter
public enum FlightClass {
    ECONOMY(0),      // 经济舱
    BUSINESS(1),     // 商务舱
    FIRST(2);        // 头等舱

    private final int code;

    FlightClass(int code) {
        this.code = code;
    }

    public static FlightClass fromCode(int code) {
        for (FlightClass flightClass : FlightClass.values()) {
            if (flightClass.getCode() == code) {
                return flightClass;
            }
        }
        throw new IllegalArgumentException("未知的 FlightClass code: " + code);
    }
}
