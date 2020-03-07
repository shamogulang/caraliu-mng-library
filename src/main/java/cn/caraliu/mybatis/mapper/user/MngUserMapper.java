package cn.caraliu.mybatis.mapper.user;

import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.domain.user.MngRoleEntity;
import cn.caraliu.mybatis.domain.user.MngUserEntity;
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

    int insert(MngUserEntity mngUserEntity);

    int batchInsert(@Param("mngUserEntities")List<MngUserEntity> mngUserEntities);

    int delete(@Param("pk") long pk);

    List<MngPermissionEntity> findPermissionByUserPk(@Param("userPk") long userPk);

    List<MngRoleEntity> findRoleByUserPk(@Param("userPk") long userPk);
}
