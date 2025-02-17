package top.johnnycse.flight.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.johnnycse.flight.utils.ApiResponse;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)// 获取字段的错误信息
                .collect(Collectors.joining(", "));// 将所有错误信息连接为一个字符串
        return ApiResponse.error(400, message);// 返回错误响应
    }
}