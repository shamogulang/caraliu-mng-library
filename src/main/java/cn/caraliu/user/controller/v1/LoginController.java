package cn.caraliu.user.controller.v1;

import cn.caraliu.user.apibean.v1.LoginReq;
import cn.caraliu.user.apibean.v1.LoginResp;
import cn.caraliu.user.dto.LoginReqDto;
import cn.caraliu.user.dto.LoginRespDto;
import cn.caraliu.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/mng/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        LoginRespDto loginRespDto = loginService.login(new LoginReqDto(loginReq));
        LoginResp loginResp = new LoginResp(loginRespDto);
        return new ResponseEntity<>(loginResp, HttpStatus.OK);
    }
}
