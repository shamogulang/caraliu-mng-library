package cn.caraliu.user.controller;

import cn.caraliu.user.apibean.v1.MngUserReq;
import cn.caraliu.user.dto.MngUserReqDto;
import cn.caraliu.user.service.MngUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/mng/v1")
public class MngUserController  {

    @Autowired
    private MngUserService mngUserService;

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('user_mgn_add1')")
    public ResponseEntity<?>  saveMngUser(@RequestBody MngUserReq mngUserReq){
        mngUserService.saveMngUser(new MngUserReqDto(mngUserReq));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    

}
