package cn.caraliu.common;

public interface ErrorConstants {

    /**
     * 错误码
     */
    int ERROR_SAVE_USER_ERROR_CODE  =  10000;
    int ERROR_INVALID_JWT_ERROR_CODE  =  10001;
    int ERROR_REJECT_ACCESS_ERROR_CODE  =  10002;
    int ERROR_LOGIN_USER_EXPIRE_TOKEN_CODE  =  10003;
    int ERROR_LOGIN_USER_INVALID_TOKEN_CODE  =  10004;
    int ERROR_LOGIN_USER_NOT_EXIST_CODE  =  10005;
    int ERROR_LOGIN_USER_INVALID_CODE  =  10006;
    int ERROR_LOGIN_USER_PASSWORD_MISMATCH_CODE  =  10007;
    int ERROR_PERMISSION_ALREADY_EXIST_CODE = 10008;

    /**
     * 错误信息
     */
    String ERROR_SAVE_USER_ERROR_MSG   =  "插入用户异常";
    String ERROR_INVALID_JWT_ERROR_MSG   =  "jwt验证不通过";
    String ERROR_REJECT_ACCESS_ERROR_MSG   =  "拒绝访问";
    String ERROR_LOGIN_USER_EXPIRE_TOKEN_MSG   =  "登录token过期";
    String ERROR_LOGIN_USER_INVALID_TOKEN_MSG  =  "登录token无效";
    String ERROR_LOGIN_USER_NOT_EXIST_MSG   =  "用户不存在";
    String ERROR_LOGIN_USER_INVALID_MSG   =  "无效用户";
    String ERROR_LOGIN_USER_PASSWORD_MISMATCH_MSG   =  "用户或者密码不匹配";
    String ERROR_PERMISSION_ALREADY_EXIST_MSG = "角色权限已经存在";

}
