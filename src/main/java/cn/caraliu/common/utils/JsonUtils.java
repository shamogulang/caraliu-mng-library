package cn.caraliu.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class JsonUtils<T> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //遇到不存的属性时直接忽略，而不是抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public  String toJsonString(Object obj) throws JsonProcessingException {
       return objectMapper.writeValueAsString(obj);
    }

    public static  <T> T parseJson(String json,Class<T>  classType) throws IOException {

        return objectMapper.readValue(json,classType);
    }
}
