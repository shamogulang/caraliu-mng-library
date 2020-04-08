package cn.caraliu.mybatis.mapper.user;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.domain.user.MngRoleEntity;
import cn.caraliu.mybatis.domain.user.MngUserEntity;
import cn.caraliu.user.dto.MngUserSearchReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MngUserMapper {

    MngUserEntity findOne(@Param("pk") long pk);

    MngUserEntity findByAccount(@Param("account") String account);

    List<MngUserEntity> findAll();

    List<MngUserEntity> findMngUsers(@Param("mngUserSearchReqDto") MngUserSearchReqDto mngUserSearchReqDto);

    int insert(MngUserEntity mngUserEntity);

    int update(@Param("mngUserEntity") MngUserEntity mngUserEntity);

    int batchInsert(@Param("mngUserEntities")List<MngUserEntity> mngUserEntities);

    int delete(@Param("pk") long pk);

    List<MngPermissionEntity> findPermissionByUserPk(@Param("userPk") long userPk);

    List<MngRoleEntity> findRoleByUserPk(@Param("userPk") long userPk);

    int updateUserRole(@Param("userPk") long userPk,@Param("rolePk") long rolePk);

    int deleteUserRole(@Param("userPk") long userPk);

    int insertUserRole(@Param("userPk") long userPk,@Param("rolePk") long rolePk);
}
