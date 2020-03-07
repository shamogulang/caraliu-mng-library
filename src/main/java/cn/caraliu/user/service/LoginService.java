package cn.caraliu.user.service;

import cn.caraliu.user.dto.LoginReqDto;
import cn.caraliu.user.dto.LoginRespDto;
import cn.caraliu.user.info.LoginUserTokenInfo;

public interface LoginService {

   LoginRespDto login(LoginReqDto loginReqDto);

   LoginUserTokenInfo getUserTokenInfo(long userPk);

   void updateUserLoginToken(long userPk);
}
