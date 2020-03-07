package cn.caraliu.user.service;

import cn.caraliu.user.dto.MngUserReqDto;

import java.util.List;

public interface MngUserService {

    void saveMngUser(MngUserReqDto mngUserReqDto);

    void saveMngUsers(List<MngUserReqDto> mngUserReqDtoList);

    void delete(long mngUserPk);
}
