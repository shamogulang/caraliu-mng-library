package cn.caraliu.user.dto;

import cn.caraliu.common.PageInfo;
import cn.caraliu.user.apibean.v1.MngUserSearchReq;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jeffchan 2020/03/28
 */
@Data
@NoArgsConstructor
public class MngUserSearchReqDto extends PageInfo {

    private String nickName;
    private String mobileNumber;
    //默认为null,表示搜索全部
    private Integer type;

    public MngUserSearchReqDto(MngUserSearchReq mngUserSearchReq) {
        this.nickName = mngUserSearchReq.getNickName();
        this.mobileNumber = mngUserSearchReq.getMobileNumber();
        this.type = mngUserSearchReq.getType();
        this.setPageNum(mngUserSearchReq.getPageNum());
        this.setPageSize(mngUserSearchReq.getPageSize());
    }
}
