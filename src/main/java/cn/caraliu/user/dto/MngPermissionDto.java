package cn.caraliu.user.dto;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MngPermissionDto {

    private long pk;
    private long parentPk;
    private String name;

    public MngPermissionDto(MngPermissionEntity mngPermissionEntity) {
        this.pk = mngPermissionEntity.getPk();
        this.parentPk = mngPermissionEntity.getParentPk();
        this.name = mngPermissionEntity.getName();
    }
}
