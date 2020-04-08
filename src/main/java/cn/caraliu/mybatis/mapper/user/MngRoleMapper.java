package cn.caraliu.mybatis.mapper.user;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.domain.user.MngRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface MngRoleMapper {

    int addRole(MngRoleEntity mngRoleEntity);

    int updateRole(MngRoleEntity mngRoleEntity);

    int deleteRole(@Param("rolePk") long rolePk);

    List<MngRoleEntity> findAll();

    MngRoleEntity findOne(@Param("rolePk") long rolePk);

    List<MngPermissionEntity> getPermissions(@Param("rolePks") List<Long> rolePks);

    /**
     * 移除角色的某个权限
     * @param rolePk
     * @param permissionPk
     * @return
     */
    int removeRolePermission(@Param("rolePk")long rolePk,@Param("permissionPk")long permissionPk);

    /**
     * 查看某个角色有效的某个权限的记录pk(用于查看该权限是否在角色中)
     * @param rolePk
     * @param permissionPk
     * @return
     */
    Integer findRolePermission(@Param("rolePk")long rolePk,@Param("permissionPk")long permissionPk);

    /**
     * 角色添加相关的权限
     * @param rolePk
     * @param permissionPk
     * @return
     */
    int addRolePermission(@Param("rolePk")long rolePk,@Param("permissionPk")long permissionPk);
}
