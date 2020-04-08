package cn.caraliu.mybatis.domain.user;

import cn.caraliu.user.dto.RoleReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MngRoleEntity {

    public long pk;
    public String name;
    private Timestamp createdAt;

    public MngRoleEntity(RoleReqDto roleReqDto) {
        this.pk = roleReqDto.getPk();
        this.name = roleReqDto.getName();
    }
}
