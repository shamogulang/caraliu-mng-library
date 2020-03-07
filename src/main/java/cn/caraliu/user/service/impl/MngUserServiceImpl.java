package cn.caraliu.user.service.impl;

import cn.caraliu.common.utils.JwtUtils;
import cn.caraliu.exception.SaveUserErrorException;
import cn.caraliu.mybatis.domain.user.MngUserEntity;
import cn.caraliu.mybatis.mapper.user.MngUserMapper;
import cn.caraliu.user.dto.MngUserReqDto;
import cn.caraliu.user.service.MngUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class MngUserServiceImpl implements MngUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MngUserMapper mngUserMapper;

    @Override
    public void saveMngUser(MngUserReqDto mngUserReqDto) {
        MngUserEntity mngUserEntity = new MngUserEntity(mngUserReqDto);
        try {
            // 密码加密
            String originPassword = mngUserReqDto.getPassword();
            byte[] salt = JwtUtils.getSalt();
            String encryptPassword = JwtUtils.encrypt(originPassword,salt );
            if(StringUtils.hasText(encryptPassword)){
                mngUserEntity.setSalt(salt);
                mngUserEntity.setPassword(encryptPassword);
                int affectRows = mngUserMapper.insert(mngUserEntity);
                if(affectRows <= 0){
                    throw  new SaveUserErrorException();
                }
            }
        }catch (Exception e){
            logger.error("saveMngUser error, for details:",e);
        }
    }

    @Override
    public void saveMngUsers(List<MngUserReqDto> mngUserReqDtoList) {

    }

    @Override
    public void delete(long mngUserPk) {

    }
}
