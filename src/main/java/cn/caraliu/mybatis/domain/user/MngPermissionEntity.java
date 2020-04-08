package cn.caraliu.mybatis.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MngPermissionEntity {

    private long pk;
    private String name;
    private String value;
    private int status;
    private long parentPk;
    private Timestamp createdAt;
    private long rolePk;

}
