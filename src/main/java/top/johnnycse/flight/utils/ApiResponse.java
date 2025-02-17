package top.johnnycse.flight.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiResponse {

    //---------------------- 成功响应 ----------------------
    public static Map<String, Object> ok() {
        return new Builder()
                .code(200)
                .message("success")
                .build();
    }

    public static Map<String, Object> ok(Object data) {
        return new Builder()
                .code(200)
                .message("success")
                .data(data)
                .build();
    }

    public static Map<String, Object> ok(String message, Object data) {
        return new Builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    //---------------------- 错误响应 ----------------------
    public static Map<String, Object> error() {
        return new Builder()
                .code(500)
                .message("server error")
                .build();
    }

    public static Map<String, Object> error(String message) {
        return new Builder()
                .code(500)
                .message(message)
                .build();
    }

    public static Map<String, Object> error(int code, String message) {
        return new Builder()
                .code(code)
                .message(message)
                .build();
    }

    //---------------------- 扩展字段（如分页）----------------------
    public static Map<String, Object> pagination(List<?> data, long total) {
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("list", data);
        pagination.put("total", total);
        return new Builder()
                .code(200)
                .message("success")
                .data(pagination)
                .build();
    }

    //---------------------- Builder模式增强灵活性 ----------------------
    private static class Builder {
        private final Map<String, Object> response = new HashMap<>();

        public Builder code(int code) {
            response.put("code", code);
            return this;
        }

        public Builder message(String message) {
            response.put("message", message);
            return this;
        }

        public Builder data(Object data) {
            response.put("data", data);
            return this;
        }

        public Builder add(String key, Object value) {
            response.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return response;
        }
    }
}
