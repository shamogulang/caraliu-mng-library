package cn.caraliu.user.apibean.v1;

import cn.caraliu.user.info.MngPermissionInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MngPermissionResp {

    private List<MngPermissionInfo> mngPermissionInfos;
}
