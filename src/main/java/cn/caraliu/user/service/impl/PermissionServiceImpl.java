package cn.caraliu.user.service.impl;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.mapper.user.MngPermissionMapper;
import cn.caraliu.user.dto.MngPermissionRespDto;
import cn.caraliu.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeffchan 2020/04/06
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private MngPermissionMapper mngPermissionMapper;

    @Override
    public List<MngPermissionRespDto> getAllPermissions() {
        List<MngPermissionRespDto> mngPermissionRespDtos = new ArrayList<>();
        List<MngPermissionEntity> mngPermissionEntities = mngPermissionMapper.findAdminPermission();
        if(mngPermissionEntities != null && !mngPermissionEntities.isEmpty()){
            mngPermissionEntities.forEach(x->{
                mngPermissionRespDtos.add(new MngPermissionRespDto(x));
            });
        }
        return mngPermissionRespDtos;
    }
}
