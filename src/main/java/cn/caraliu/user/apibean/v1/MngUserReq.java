package cn.caraliu.user.apibean.v1;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class MngUserReq {

    private long pk;
    private String nickName;
    private String account;
    private String password;
    private String mobileNumber;
    private int type;
}
