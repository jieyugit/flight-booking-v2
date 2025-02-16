package top.johnnycse.flight.utils;


import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {
    /**
     * 获取request中的参数集合转对象
     * 用法：User user = (User) RequestUtil.getParameterObject(request, new User())
     * @param request obj
     * @return
     */
    public static Object getParameterObject(HttpServletRequest request, Object obj) {
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.copyProperties(obj,map);
        return obj;
    }
    /**
     * 获取request中的参数集合转Map
     * Map<String,String> parameterMap = RequestUtil.getParameterMap(request)
     * @param request
     * @return
     */
    public Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }
}

