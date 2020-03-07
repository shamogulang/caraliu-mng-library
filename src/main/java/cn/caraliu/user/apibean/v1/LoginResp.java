package cn.caraliu.user.apibean.v1;

import cn.caraliu.user.dto.LoginRespDto;
import cn.caraliu.user.dto.MngPermissionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LoginResp {
    private long pk;
    private String username;
    private String jwt;
    //权限列表
    private List<MngPermissionResp>  mngPermissionResps;

    public LoginResp(LoginRespDto loginRespDto) {
        this.pk = loginRespDto.getPk();
        this.jwt = loginRespDto.getJwt();
        this.username = loginRespDto.getUsername();
        List<MngPermissionDto> mngPermissionDtos = loginRespDto.getMngPermissionDtos();
        if(mngPermissionDtos != null && !mngPermissionDtos.isEmpty()){
            List<MngPermissionResp> mngPermissionResps = new ArrayList<>();
            mngPermissionDtos.forEach(x->{
                mngPermissionResps.add(new MngPermissionResp(x));
            });
            this.mngPermissionResps = mngPermissionResps;
        }
    }
}
