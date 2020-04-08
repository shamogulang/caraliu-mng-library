package cn.caraliu.user.service;

import cn.caraliu.user.dto.MngPermissionRespDto;

import java.util.List;

/**
 * @author jeffchan 2020/04/06
 */
public interface PermissionService {

    List<MngPermissionRespDto>  getAllPermissions();
}
