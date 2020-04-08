package cn.caraliu.user.info;

import cn.caraliu.user.dto.MngPermissionRespDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MngPermissionInfo {

    private long pk;
    private long parentPk;
    private String name;
    private String value;
    private long createdAt;
    private List<MngPermissionInfo> childPermissions;

    public MngPermissionInfo(MngPermissionRespDto mngPermissionRespDto) {
        this.pk = mngPermissionRespDto.getPk();
        this.parentPk = mngPermissionRespDto.getParentPk();
        this.name = mngPermissionRespDto.getName();
        this.value = mngPermissionRespDto.getValue();
        this.createdAt = mngPermissionRespDto.getCreatedAt();
    }
}
