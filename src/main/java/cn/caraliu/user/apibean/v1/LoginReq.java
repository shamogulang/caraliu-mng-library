package cn.caraliu.user.apibean.v1;

import lombok.Data;

@Data
public class LoginReq {

    private String account;
    private String password;
}
