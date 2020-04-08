package cn.caraliu.user.dto;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MngPermissionRespDto {

    private long pk;
    private long parentPk;
    private String name;
    private String value;
    private long createdAt;

    public MngPermissionRespDto(MngPermissionEntity mngPermissionEntity) {
        this.pk = mngPermissionEntity.getPk();
        this.parentPk = mngPermissionEntity.getParentPk();
        this.name = mngPermissionEntity.getName();
        this.value = mngPermissionEntity.getValue();
        this.createdAt = mngPermissionEntity.getCreatedAt().getTime();
    }
}
