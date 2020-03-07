package cn.caraliu.mybatis.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MngPermissionEntity {

    private long pk;
    private String name;
    private int status;
    private long parentPk;

}
