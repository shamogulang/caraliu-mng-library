package cn.caraliu.user.apibean.v1;

import cn.caraliu.common.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jeffchan 2020/03/28
 */
@Data
@NoArgsConstructor
public class MngUserSearchReq extends PageInfo {

    private String nickName;
    private String mobileNumber;
    //默认为null,表示搜索全部
    private Integer type;

}
