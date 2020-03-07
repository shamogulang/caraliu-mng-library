package cn.caraliu.mybatis.mapper.user;

import cn.caraliu.mybatis.domain.user.MngRoleEntity;

public interface MngRoleMapper {

    int addRole(MngRoleEntity mngRoleEntity);

    int deleteRole(MngRoleEntity mngRoleEntity);
}
