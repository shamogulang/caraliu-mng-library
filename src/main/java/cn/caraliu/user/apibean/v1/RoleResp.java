package cn.caraliu.user.apibean.v1;

import cn.caraliu.user.info.RoleInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jeffchan 2020/03/29
 */
@Data
@NoArgsConstructor
public class RoleResp {

    private List<RoleInfo> roleInfos;
}
