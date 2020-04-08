package cn.caraliu.user.apibean.v1;

import cn.caraliu.common.PageInfo;
import cn.caraliu.user.info.MngUserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jeffchan 2020/03/28
 */
@Data
@NoArgsConstructor
public class MngUserResp extends PageInfo {

    private List<MngUserInfo> mngUserInfos;
}
