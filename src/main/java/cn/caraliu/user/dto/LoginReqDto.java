package cn.caraliu.user.dto;

import cn.caraliu.user.apibean.v1.LoginReq;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginReqDto {

    private String account;
    private String password;

    public LoginReqDto(LoginReq loginReq) {
        this.account = loginReq.getAccount();
        this.password = loginReq.getPassword();
    }
}
