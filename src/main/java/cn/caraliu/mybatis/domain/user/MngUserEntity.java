package cn.caraliu.mybatis.domain.user;

import cn.caraliu.user.dto.MngUserReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MngUserEntity {

    private long pk;
    private String nickName;
    private String account;
    private String password;
    private String mobileNumber;
    private int status;
    private int type;
    private byte[] salt;

    public MngUserEntity(MngUserReqDto mngUserReqDto) {
        this.nickName = mngUserReqDto.getNickName();
        this.account = mngUserReqDto.getAccount();
        this.mobileNumber = mngUserReqDto.getMobileNumber();
        this.type = mngUserReqDto.getType();
    }

    public enum Status{
        DISABLE,
        ENABLE
    }

    public enum Type{
        NORMAl,// 普通用户
        MNG// 管理员
    }

}
