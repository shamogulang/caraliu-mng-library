package cn.caraliu.user.controller.v1;

import cn.caraliu.user.apibean.v1.MngUserReq;
import cn.caraliu.user.apibean.v1.MngUserResp;
import cn.caraliu.user.apibean.v1.MngUserSearchReq;
import cn.caraliu.user.dto.MngUserReqDto;
import cn.caraliu.user.dto.MngUserRespDto;
import cn.caraliu.user.dto.MngUserSearchReqDto;
import cn.caraliu.user.dto.RoleRespDto;
import cn.caraliu.user.info.MngUserInfo;
import cn.caraliu.user.info.RoleInfo;
import cn.caraliu.user.service.MngUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/mng/v1")
public class MngUserController  {

    @Autowired
    private MngUserService mngUserService;

    /**
     * 添加用户
     * @param mngUserReq
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('user_mng_add')")
    public ResponseEntity<?>  saveMngUser(@RequestBody MngUserReq mngUserReq){
        mngUserService.addMngUser(new MngUserReqDto(mngUserReq));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * 修改用户
     * @param mngUserReq
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('user_mgn_update')")
    public ResponseEntity<?>  updateMngUser(@RequestBody MngUserReq mngUserReq){
        mngUserService.updateMngUser(new MngUserReqDto(mngUserReq));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * 获取用户列表
     * @param mngUserSearchReq
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('user_mgn_list')")
    public ResponseEntity<?> getUsers(@RequestBody MngUserSearchReq mngUserSearchReq){

        List<MngUserInfo> mngUserInfos = new ArrayList<>();
        Pair<List<MngUserRespDto>,Integer> mngUserRespPage = mngUserService.getMngUsers(new MngUserSearchReqDto(mngUserSearchReq));
        List<MngUserRespDto> mngUserRespDtos = mngUserRespPage.getFirst();
        if(mngUserRespDtos != null && !mngUserRespDtos.isEmpty()){
            mngUserRespDtos.forEach(x->{
                mngUserInfos.add(new MngUserInfo(x));
            });
        }
        MngUserResp mngUserResp = new MngUserResp();
        mngUserResp.setMngUserInfos(mngUserInfos);
        mngUserResp.setPageNum(mngUserSearchReq.getPageNum());
        mngUserResp.setPageSize(mngUserSearchReq.getPageSize());
        //设置列表总数
        mngUserResp.setTotal(mngUserRespPage==null?0:mngUserRespPage.getSecond().intValue());
        return new ResponseEntity<>(mngUserResp,HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userPk}",method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable long userPk){
        MngUserRespDto mngUserRespDto = mngUserService.getMngUser(userPk);
        MngUserInfo mngUserInfo = new MngUserInfo(mngUserRespDto);
        return new ResponseEntity<>(mngUserInfo,HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userPk}",method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteUser(@PathVariable long userPk){
        mngUserService.delete(userPk);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @RequestMapping(value = "/user/role/{userPk}",method = RequestMethod.GET)
    public ResponseEntity<?> getUserRole(@PathVariable long userPk){
        RoleRespDto roleRespDto = mngUserService.getUserRole(userPk);
        RoleInfo roleInfo = null;
        if(roleRespDto != null){
            roleInfo = new RoleInfo(roleRespDto);
        }
        return new ResponseEntity<>(roleInfo,HttpStatus.OK);
    }

    @RequestMapping(value = "/user/role/{userPk}/{rolePk}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserRole(@PathVariable long userPk,@PathVariable long rolePk){

        if(userPk > 0L){
            RoleRespDto roleRespDto = mngUserService.getUserRole(userPk);
            if(roleRespDto == null && rolePk > 0L){
                mngUserService.insertUserRole(userPk,rolePk);
            }else{
                if(roleRespDto.getPk() != rolePk){
                    if(rolePk > 0L){
                        mngUserService.updateUserRole(userPk,rolePk);
                    }else{
                        mngUserService.deleteUserRole(userPk);
                    }
                }
            }
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
