package cn.caraliu.user.service.impl;

import cn.caraliu.common.utils.JwtUtils;
import cn.caraliu.exception.SaveUserErrorException;
import cn.caraliu.exception.UserNotExistException;
import cn.caraliu.mybatis.domain.user.MngRoleEntity;
import cn.caraliu.mybatis.domain.user.MngUserEntity;
import cn.caraliu.mybatis.mapper.user.MngUserMapper;
import cn.caraliu.user.dto.MngUserReqDto;
import cn.caraliu.user.dto.MngUserRespDto;
import cn.caraliu.user.dto.MngUserSearchReqDto;
import cn.caraliu.user.dto.RoleRespDto;
import cn.caraliu.user.service.MngUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MngUserServiceImpl implements MngUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MngUserMapper mngUserMapper;

    @Override
    public void addMngUser(MngUserReqDto mngUserReqDto) {
        MngUserEntity mngUserEntity = new MngUserEntity(mngUserReqDto);
        try {
            // 密码加密
            String originPassword = mngUserReqDto.getPassword();
            byte[] salt = JwtUtils.getSalt();
            String encryptPassword = JwtUtils.encrypt(originPassword, salt);
            if (StringUtils.hasText(encryptPassword)) {
                mngUserEntity.setSalt(salt);
                mngUserEntity.setPassword(encryptPassword);
                int affectRows = mngUserMapper.insert(mngUserEntity);
                if (affectRows <= 0) {
                    throw new SaveUserErrorException();
                }
            }
        } catch (Exception e) {
            logger.error("saveMngUser error, for details:", e);
        }
    }

    @Override
    public void updateMngUser(MngUserReqDto mngUserReqDto) {
        MngUserEntity mngUserEntity = new MngUserEntity(mngUserReqDto);
        try {
            int affectRows = mngUserMapper.update(mngUserEntity);
            if (affectRows <= 0) {
                throw new SaveUserErrorException();
            }
        } catch (Exception e) {
            logger.error("updateMngUser error, for details:", e);
        }
    }

    @Override
    public MngUserRespDto getMngUser(long userPk) {
        return new MngUserRespDto(mngUserMapper.findOne(userPk));
    }

    @Override
    public Pair<List<MngUserRespDto>, Integer> getMngUsers(MngUserSearchReqDto mngUserSearchReqDto) {
        List<MngUserRespDto> mngUserRespDtos = new ArrayList<>();
        PageHelper.startPage(mngUserSearchReqDto.getPageNum(), mngUserSearchReqDto.getPageSize());
        List<MngUserEntity> mngUserEntities = mngUserMapper.findMngUsers(mngUserSearchReqDto);
        PageInfo<MngUserEntity> resultPage = new PageInfo<>(mngUserEntities);
        if (mngUserEntities != null && !mngUserEntities.isEmpty()) {
            mngUserEntities.forEach(x -> {
                mngUserRespDtos.add(new MngUserRespDto(x));
            });
        }
        return Pair.of(mngUserRespDtos, (int) resultPage.getTotal());
    }

    @Override
    public void saveMngUsers(List<MngUserReqDto> mngUserReqDtoList) {

    }

    @Override
    public void delete(long mngUserPk) {
        int affectRows = mngUserMapper.delete(mngUserPk);
        if (affectRows <= 0) {
            throw new UserNotExistException();
        }
    }

    @Override
    public RoleRespDto getUserRole(long userPk) {
        RoleRespDto roleRespDto = null;
        List<MngRoleEntity> mngRoleEntities = mngUserMapper.findRoleByUserPk(userPk);
        if(mngRoleEntities != null && !mngRoleEntities.isEmpty()){
            MngRoleEntity mngRoleEntity = mngRoleEntities.get(0);
            roleRespDto = new RoleRespDto(mngRoleEntity);
        }
        return roleRespDto;
    }

    @Override
    public void updateUserRole(long userPk, long rolePk) {
        mngUserMapper.updateUserRole(userPk,rolePk);
    }

    @Override
    public void insertUserRole(long userPk, long rolePk) {
        mngUserMapper.insertUserRole(userPk,rolePk);
    }

    @Override
    public void deleteUserRole(long userPk) {
        mngUserMapper.deleteUserRole(userPk);
    }
}
