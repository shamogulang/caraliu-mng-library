package cn.caraliu.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginRespDto {

    private long pk;
    private String username;
    private String token;
    private List<MngPermissionRespDto> mngPermissionRespDtos;
}
