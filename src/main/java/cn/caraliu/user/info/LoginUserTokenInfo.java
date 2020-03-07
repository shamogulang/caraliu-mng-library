package cn.caraliu.user.info;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserTokenInfo {

    private String token;
    private long userPk;

}
