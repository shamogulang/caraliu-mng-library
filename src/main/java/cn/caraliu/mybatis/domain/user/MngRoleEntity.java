package cn.caraliu.mybatis.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MngRoleEntity {

    public long pk;
    public String name;
    private int status;

}
