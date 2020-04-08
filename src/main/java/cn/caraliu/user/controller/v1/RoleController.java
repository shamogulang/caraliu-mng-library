package cn.caraliu.user.controller.v1;

import cn.caraliu.user.apibean.v1.RoleReq;
import cn.caraliu.user.apibean.v1.RoleResp;
import cn.caraliu.user.dto.RoleReqDto;
import cn.caraliu.user.dto.RoleRespDto;
import cn.caraliu.user.info.RoleInfo;
import cn.caraliu.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeffchan 2020/03/29
 */
@Controller
@RequestMapping(value = "/api/mng/v1")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     * @return
     */
    @RequestMapping(value = "/roles",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('user_role_list')")
    public ResponseEntity<?>  getRoles(){
        RoleResp roleResp = new RoleResp();
        List<RoleInfo> roleInfos = new ArrayList<>();
        List<RoleRespDto> roleRespDtos =  roleService.getRoles();
        if(roleRespDtos != null && !roleRespDtos.isEmpty()){
            roleRespDtos.forEach(x->{
                roleInfos.add(new RoleInfo(x));
            });
        }
        roleResp.setRoleInfos(roleInfos);
        return new ResponseEntity<>(roleResp, HttpStatus.OK);
    }

    @RequestMapping(value = "/role",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('user_role_add')")
    public ResponseEntity<?> addRole(@RequestBody RoleReq roleReq){
        roleService.addRole(new RoleReqDto(roleReq));
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/role",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('user_role_update')")
    public ResponseEntity<?> updateRole(@RequestBody RoleReq roleReq){
        roleService.updateRole(new RoleReqDto(roleReq));
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{rolePk}",method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('user_role_delete')")
    public ResponseEntity<?> deleteRole(@PathVariable long rolePk){
        roleService.deleteRole(rolePk);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{rolePk}",method = RequestMethod.GET)
    public ResponseEntity<?> getRole(@PathVariable long rolePk){
        RoleRespDto roleRespDto = roleService.getRole(rolePk);
        RoleInfo roleInfo = new RoleInfo(roleRespDto);
        return new ResponseEntity<>(roleInfo,HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{rolePk}/permission/{permissionPk}",method = RequestMethod.POST)
    public ResponseEntity<?> addRolePermission(@PathVariable long rolePk,@PathVariable long permissionPk){
        roleService.addRolePermission(rolePk,permissionPk);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{rolePk}/permission/{permissionPk}",method = RequestMethod.DELETE)
    public ResponseEntity<?> removeRolePermission(@PathVariable long rolePk,@PathVariable long permissionPk){

        roleService.removeRolePermission(rolePk,permissionPk);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
