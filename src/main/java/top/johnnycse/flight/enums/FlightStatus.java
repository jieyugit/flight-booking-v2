package top.johnnycse.flight.enums;

import lombok.Getter;

@Getter
public enum FlightStatus {
    PLANNED(0),      // 计划中
    DEPARTED(1),     // 已起飞
    CANCELLED(2);    // 已取消

    private final int code;

    FlightStatus(int code) {
        this.code = code;
    }

    public static FlightStatus fromCode(int code) {
        for (FlightStatus status : FlightStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的 FlightStatus code: " + code);
    }
}
