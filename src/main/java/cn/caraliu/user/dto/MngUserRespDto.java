package cn.caraliu.user.dto;

import cn.caraliu.mybatis.domain.user.MngUserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jeffchan 2020/03/28
 */
@Data
@NoArgsConstructor
public class MngUserRespDto {

    private long pk;
    private String nickName;
    private String account;
    private String mobileNumber;
    private int type;
    private int status;
    private long createdAt;

    public MngUserRespDto(MngUserEntity mngUserEntity) {
        this.pk = mngUserEntity.getPk();
        this.nickName = mngUserEntity.getNickName();
        this.account = mngUserEntity.getAccount();
        this.mobileNumber = mngUserEntity.getMobileNumber();
        this.type = mngUserEntity.getType();
        this.status = mngUserEntity.getStatus();
        this.createdAt = mngUserEntity.getCreatedAt().getTime();
    }
}
