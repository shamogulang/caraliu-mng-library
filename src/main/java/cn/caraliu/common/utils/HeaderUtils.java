package cn.caraliu.common.utils;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HeaderUtils {

    public static final String HEADER_X_FORWARDED_FOR = "X-FORWARDED-FOR";
    public static final String X_CARALIU_USER_ID = "X-CARALIU-USER-ID";
    public static final String X_CARALIU_USER_PK = "X-CARALIU-USER-PK";
    public static final String AUTHORIZATION = "Authorization";

    public static String getHeaderValue(HttpHeaders headers,String key) {
        String userId = null;
        List<String> userIds = headers.get(key);
        if(userIds != null && !userIds.isEmpty()){
            userId = userIds.get(0);
        }
        return  userId != null ? userId.replaceAll("^\"|\"$", "") : userId;
    }

    public static String getRequestHeaderValue(HttpServletRequest request, String key) {
        String value = request.getHeader(key);
        return  value != null ? value.replaceAll("^\"|\"$", "") : value;
    }

    /**
     * 如果目标串有双引号，去除
     * @param target
     * @return
     */
    private static String trimDoubleQuote(String target){
        return  target != null ? target.replaceAll("^\"|\"$", "") : target;
    }
}
