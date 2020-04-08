package cn.caraliu.user.info;

import cn.caraliu.user.dto.RoleRespDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jeffchan 2020/04/06
 */
@Data
@NoArgsConstructor
public class RoleInfo {

    private long pk;
    private String name;
    private long createdAt;
    private List<MngPermissionInfo> mngPermissionInfos;

    public RoleInfo(RoleRespDto roleRespDto) {
        this.pk = roleRespDto.getPk();
        this.name = roleRespDto.getName();
        this.createdAt = roleRespDto.getCreatedAt();
    }

}
