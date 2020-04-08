package cn.caraliu.user.service;

import cn.caraliu.user.dto.MngUserReqDto;
import cn.caraliu.user.dto.MngUserRespDto;
import cn.caraliu.user.dto.MngUserSearchReqDto;
import cn.caraliu.user.dto.RoleRespDto;
import org.springframework.data.util.Pair;

import java.util.List;

public interface MngUserService {

    void addMngUser(MngUserReqDto mngUserReqDto);

    void updateMngUser(MngUserReqDto mngUserReqDto);

    void saveMngUsers(List<MngUserReqDto> mngUserReqDtoList);

    void delete(long mngUserPk);

    MngUserRespDto getMngUser(long userPk);

    Pair<List<MngUserRespDto>,Integer> getMngUsers(MngUserSearchReqDto mngUserReqDto);

    RoleRespDto getUserRole(long userPk);

    void updateUserRole(long userPk,long rolePk);

    void deleteUserRole(long userPk);

    void insertUserRole(long userPk,long rolePk);
}
