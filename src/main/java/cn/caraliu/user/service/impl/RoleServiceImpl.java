package cn.caraliu.user.service.impl;

import cn.caraliu.exception.PermissionAlreadyExistException;
import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.domain.user.MngRoleEntity;
import cn.caraliu.mybatis.mapper.user.MngRoleMapper;
import cn.caraliu.user.dto.RoleReqDto;
import cn.caraliu.user.dto.RoleRespDto;
import cn.caraliu.user.info.MngPermissionInfo;
import cn.caraliu.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jeffchan 2020/03/29
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private MngRoleMapper mngRoleMapper;

    @Override
    public List<RoleRespDto> getRoles() {
        List<RoleRespDto> roleRespDtos = new ArrayList<>();
        List<MngRoleEntity> mngRoleEntities = mngRoleMapper.findAll();
        List<Long> rolePks = new ArrayList<>();
        if(mngRoleEntities != null && !mngRoleEntities.isEmpty()){
            mngRoleEntities.forEach(x->{
                rolePks.add(x.getPk());
                roleRespDtos.add(new RoleRespDto(x));
            });
        }

        // 设置权限
        List<MngPermissionEntity> mngPermissionEntities = mngRoleMapper.getPermissions(rolePks);

        Map<Long,List<MngPermissionInfo>> rolePermissionMap = new HashMap<>();

        return roleRespDtos;
    }

    @Override
    public void addRole(RoleReqDto roleReqDto) {
        MngRoleEntity mngRoleEntity = new MngRoleEntity(roleReqDto);
        mngRoleMapper.addRole(mngRoleEntity);
    }

    @Override
    public void updateRole(RoleReqDto roleReqDto) {
        MngRoleEntity mngRoleEntity = new MngRoleEntity(roleReqDto);
        mngRoleMapper.updateRole(mngRoleEntity);
    }

    @Override
    public void deleteRole(long rolePk) {
        mngRoleMapper.deleteRole(rolePk);
    }

    @Override
    public RoleRespDto getRole(long rolePk) {
        MngRoleEntity mngRoleEntity = mngRoleMapper.findOne(rolePk);
        RoleRespDto roleRespDto = new RoleRespDto(mngRoleEntity);
        return roleRespDto;
    }

    @Override
    public void removeRolePermission(long rolePk, long permissionPk) {
        mngRoleMapper.removeRolePermission(rolePk,permissionPk);
    }

    @Override
    public void addRolePermission(long rolePk, long permissionPk) {
        Integer recordPk = mngRoleMapper.findRolePermission(rolePk,permissionPk);
        if(recordPk == null || recordPk.intValue() > 0){
            mngRoleMapper.addRolePermission(rolePk,permissionPk);
        }else{
           throw new PermissionAlreadyExistException();
        }
    }
}
