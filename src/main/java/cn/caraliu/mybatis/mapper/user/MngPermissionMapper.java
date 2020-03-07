package cn.caraliu.mybatis.mapper.user;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface MngPermissionMapper {

    int addPermission(MngPermissionEntity mngPermissionEntity);

    int deletePermission(long pk);

    List<MngPermissionEntity>  findAdminPermission();
}
