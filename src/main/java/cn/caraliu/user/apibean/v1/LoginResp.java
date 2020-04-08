package cn.caraliu.user.apibean.v1;

import cn.caraliu.user.dto.LoginRespDto;
import cn.caraliu.user.dto.MngPermissionRespDto;
import cn.caraliu.user.info.MngPermissionInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LoginResp {
    private long pk;
    private String username;
    private String token;
    //权限列表
    private List<MngPermissionInfo>  mngPermissionInfos;

    public LoginResp(LoginRespDto loginRespDto) {
        this.pk = loginRespDto.getPk();
        this.token = loginRespDto.getToken();
        this.username = loginRespDto.getUsername();
        List<MngPermissionRespDto> mngPermissionRespDtos = loginRespDto.getMngPermissionRespDtos();
        if(mngPermissionRespDtos != null && !mngPermissionRespDtos.isEmpty()){
            List<MngPermissionInfo> mngPermissionInfos = new ArrayList<>();
            mngPermissionRespDtos.forEach(x->{
                mngPermissionInfos.add(new MngPermissionInfo(x));
            });
            this.mngPermissionInfos = mngPermissionInfos;
        }
    }
}
