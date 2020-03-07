package cn.caraliu.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomErrorResponse {

    private int errorCode;
    private String errorMessage;
}
