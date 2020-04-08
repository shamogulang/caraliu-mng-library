package cn.caraliu.user.info;

import cn.caraliu.user.dto.MngUserRespDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jeffchan 2020/03/28
 */
@Data
@NoArgsConstructor
public class MngUserInfo {

    private long pk;
    private String nickName;
    private String account;
    private String mobileNumber;
    private int type;
    private int status;
    private long createdAt;

    public MngUserInfo(MngUserRespDto mngUserRespDto) {
        this.pk = mngUserRespDto.getPk();
        this.nickName = mngUserRespDto.getNickName();
        this.account = mngUserRespDto.getAccount();
        this.mobileNumber = mngUserRespDto.getMobileNumber();
        this.type = mngUserRespDto.getType();
        this.status = mngUserRespDto.getStatus();
        this.createdAt = mngUserRespDto.getCreatedAt();
    }
}
