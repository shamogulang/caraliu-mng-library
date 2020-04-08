package cn.caraliu.user.dto;

import cn.caraliu.mybatis.domain.user.MngRoleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author jeffchan 2020/03/29
 */
@Data
@NoArgsConstructor
public class RoleRespDto {

    public long pk;
    public String name;
    private long createdAt;

    public RoleRespDto(MngRoleEntity mngRoleEntity) {
        this.pk = mngRoleEntity.getPk();
        this.name = mngRoleEntity.getName();
        this.createdAt = mngRoleEntity.getCreatedAt().getTime();
    }
}
