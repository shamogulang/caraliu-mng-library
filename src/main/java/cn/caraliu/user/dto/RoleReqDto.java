package cn.caraliu.user.dto;

import cn.caraliu.user.apibean.v1.RoleReq;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jeffchan 2020/04/06
 */
@Data
@NoArgsConstructor
public class RoleReqDto {

    private long pk;
    private String name;

    public RoleReqDto(RoleReq roleReq) {
        this.pk = roleReq.getPk();
        this.name = roleReq.getName();
    }
}
