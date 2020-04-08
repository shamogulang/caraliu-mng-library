package cn.caraliu.user.service;

import cn.caraliu.user.dto.RoleReqDto;
import cn.caraliu.user.dto.RoleRespDto;

import java.util.List;

/**
 * @author jeffchan 2020/03/29
 */
public interface RoleService {

    List<RoleRespDto> getRoles();

    void addRole(RoleReqDto roleReqDto);

    void updateRole(RoleReqDto roleReqDto);

    void deleteRole(long rolePk);

    RoleRespDto getRole(long rolePk);

    void removeRolePermission(long rolePk,long permissionPk);

    void addRolePermission(long rolePk,long permissionPk);
}
