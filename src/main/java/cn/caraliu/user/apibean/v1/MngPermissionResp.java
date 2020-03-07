package cn.caraliu.user.apibean.v1;

import cn.caraliu.user.dto.MngPermissionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MngPermissionResp {

    private long pk;
    private long parentPk;
    private String name;

    public MngPermissionResp(MngPermissionDto mngPermissionDto) {
        this.pk = mngPermissionDto.getPk();
        this.parentPk = mngPermissionDto.getParentPk();
        this.name = mngPermissionDto.getName();
    }
}
