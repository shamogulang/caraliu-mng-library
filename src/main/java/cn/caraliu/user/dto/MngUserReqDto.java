package cn.caraliu.user.dto;

import cn.caraliu.user.apibean.v1.MngUserReq;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MngUserReqDto {

    private long pk;
    private String nickName;
    private String account;
    private String password;
    private String mobileNumber;
    private int type;

    public MngUserReqDto(MngUserReq mngUserReq) {
        this.nickName = mngUserReq.getNickName();
        this.account = mngUserReq.getAccount();
        this.password = mngUserReq.getPassword();
        this.mobileNumber = mngUserReq.getMobileNumber();
        this.type = mngUserReq.getType();
    }
}
