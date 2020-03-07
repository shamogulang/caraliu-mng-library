package cn.caraliu.user.service.impl;

import cn.caraliu.common.redis.Constant;
import cn.caraliu.common.redis.utils.StringRedisUtils;
import cn.caraliu.common.utils.JsonUtils;
import cn.caraliu.common.utils.JwtUtils;
import cn.caraliu.common.utils.Md5Utils;
import cn.caraliu.exception.UserInvalidException;
import cn.caraliu.exception.UserNotExistException;
import cn.caraliu.exception.UserOrPasswordMismatchException;
import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.domain.user.MngUserEntity;
import cn.caraliu.mybatis.mapper.user.MngPermissionMapper;
import cn.caraliu.mybatis.mapper.user.MngUserMapper;
import cn.caraliu.user.apibean.v1.LoginResp;
import cn.caraliu.user.dto.LoginReqDto;
import cn.caraliu.user.dto.LoginRespDto;
import cn.caraliu.user.dto.MngPermissionDto;
import cn.caraliu.user.info.LoginUserTokenInfo;
import cn.caraliu.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    public StringRedisUtils stringRedisUtils;
    @Autowired
    private MngUserMapper mngUserMapper;
    @Autowired
    private MngPermissionMapper mngPermissionMapper;


    private void verifyPassword(MngUserEntity userEntity, String password) {
        byte[] salt = userEntity.getSalt();
        String passwordInDB = userEntity.getPassword();
        if (StringUtils.hasText(passwordInDB) && salt != null) {
            String loginFail = String.format(Constant.REDIS_USER_LOGIN_FAIL, userEntity.getAccount());
            if (JwtUtils.encrypt(password, salt).equals(passwordInDB)) {
                stringRedisUtils.delete(Arrays.asList(loginFail));
            } else {
                String loginCnt = stringRedisUtils.getString(loginFail);
                if (loginCnt == null || "".equals(loginCnt)) {
                    stringRedisUtils.setString(loginFail, "1", 1L, TimeUnit.MINUTES);
                } else {
                    stringRedisUtils.increaseString(loginFail, 1L);
                }
                throw new UserOrPasswordMismatchException();
            }
        } else {
            throw new UserOrPasswordMismatchException();
        }
    }

    @Override
    public LoginRespDto login(LoginReqDto loginReqDto) {
        LoginRespDto loginRespDto = new LoginRespDto();
        try {
            MngUserEntity userEntity = mngUserMapper.findByAccount(loginReqDto.getAccount());
            if (userEntity == null) {
                throw new UserNotExistException();
            } else if (userEntity.getStatus() == MngUserEntity.Status.DISABLE.ordinal()) {
                throw new UserInvalidException();
            }

            //校验密码，不正确抛出异常
            this.verifyPassword(userEntity, loginReqDto.getPassword());

            Map<String,String> tokenMaps = new HashMap<>(2);
            String token = UUID.randomUUID().toString();
            tokenMaps.put("userToken",token);
            tokenMaps.put("userType","mngUser");
            String userPkMd5Hex = Md5Utils.md5Encode(String.valueOf(userEntity.getPk()));
            String redisKeyLoginToken = String.format(Constant.REDIS_USER_LOGIN_TOKEN,userPkMd5Hex);
            stringRedisUtils.setString(redisKeyLoginToken,token,0,null);
            updateUserLoginToken(userEntity.getPk());

            String jwtToken = JwtUtils.generateJWT(String.valueOf(userEntity.getPk()), -1, tokenMaps);

            loginRespDto.setPk(userEntity.getPk());
            loginRespDto.setJwt(jwtToken);
            loginRespDto.setUsername(userEntity.getNickName());

            //记录用户登录的历史信息
            //this.userJdbc.updateLatestLogin(userEntity.getId(), ip, this.tableNameConfig);
            List<MngPermissionDto> mngPermissionDtos = new ArrayList<>();
            List<MngPermissionEntity> mngPermissionEntities = null;
            if (userEntity.getType() == MngUserEntity.Type.MNG.ordinal()) {
                mngPermissionEntities = mngPermissionMapper.findAdminPermission();
            } else {
                mngPermissionEntities = mngUserMapper.findPermissionByUserPk(userEntity.getPk());
            }
            if (!mngPermissionEntities.isEmpty()) {
                mngPermissionEntities.forEach(x -> {
                    mngPermissionDtos.add(new MngPermissionDto(x));
                });
                loginRespDto.setMngPermissionDtos(mngPermissionDtos);
            }
        } catch (Exception e) {
            log.error("login error,for details:", e);
        }
        return loginRespDto;
    }

    @Override
    public LoginUserTokenInfo getUserTokenInfo(long userPk) {
        LoginUserTokenInfo loginUserTokenInfo = null;
        String userPkMd5Hex = Md5Utils.md5Encode(String.valueOf(userPk));
        try {
            String result = String.format(Constant.REDIS_USER_LOGIN_TOKEN, userPkMd5Hex);
            String userTokenInfoJson = stringRedisUtils.getString(result);
            if (StringUtils.hasText(userTokenInfoJson)) {
                loginUserTokenInfo = new LoginUserTokenInfo();
                loginUserTokenInfo.setUserPk(userPk);
                loginUserTokenInfo.setToken(userTokenInfoJson);
            }
        } catch (Exception e) {
            log.error("getUserTokenInfo error, for details:", e);
        }
        return loginUserTokenInfo;
    }

    /**
     * 每次操作更新记录时间，超过33分钟没操作，则抛出异常，需要重新登录
     *
     * @param userPk
     */
    @Override
    public void updateUserLoginToken(long userPk) {
        String userPkMd5Hex = Md5Utils.md5Encode(String.valueOf(userPk));
        String result = String.format(Constant.REDIS_USER_LOGIN_TOKEN, userPkMd5Hex);
        stringRedisUtils.expire(result, 33L, TimeUnit.MINUTES);
    }
}
