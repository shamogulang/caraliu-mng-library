package cn.caraliu.user.controller.v1;

import cn.caraliu.user.apibean.v1.MngPermissionResp;
import cn.caraliu.user.dto.MngPermissionRespDto;
import cn.caraliu.user.info.MngPermissionInfo;
import cn.caraliu.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeffchan 2020/04/06
 */
@Controller
@RequestMapping("/api/mng/v1")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/permissions",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('user_permission_list')")
    public ResponseEntity<?> getAllPermissions(){
        MngPermissionResp mngPermissionResp = new MngPermissionResp();
        List<MngPermissionInfo> mngPermissionInfos = new ArrayList<>();
        List<MngPermissionRespDto> mngPermissionRespDtos = permissionService.getAllPermissions();
        if(mngPermissionRespDtos != null && !mngPermissionRespDtos.isEmpty()){
            mngPermissionRespDtos.forEach(x->{
                mngPermissionInfos.add(new MngPermissionInfo(x));
            });
        }
        mngPermissionResp.setMngPermissionInfos(mngPermissionInfos);
        return new ResponseEntity<>(mngPermissionResp, HttpStatus.OK);
    }
}
