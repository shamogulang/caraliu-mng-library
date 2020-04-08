package cn.caraliu.exception.handler;

import cn.caraliu.common.CustomErrorResponse;
import cn.caraliu.common.ErrorConstants;
import cn.caraliu.exception.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SaveUserErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse saveUserErrorExceptionHandler(SaveUserErrorException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_SAVE_USER_ERROR_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_SAVE_USER_ERROR_MSG);
        return errorResponse;
    }

    @ExceptionHandler(InvalidJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse invalidJwtExceptionHandler(InvalidJwtException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_INVALID_JWT_ERROR_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_INVALID_JWT_ERROR_MSG);
        return errorResponse;
    }

    @ExceptionHandler(RejectAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse rejectAccessExceptionHandler(RejectAccessException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_REJECT_ACCESS_ERROR_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_REJECT_ACCESS_ERROR_MSG);
        return errorResponse;
    }

    @ExceptionHandler(LoginUserExpireTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse loginUserExpireTokenExceptionHandler(LoginUserExpireTokenException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_LOGIN_USER_EXPIRE_TOKEN_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_LOGIN_USER_EXPIRE_TOKEN_MSG);
        return errorResponse;
    }

    @ExceptionHandler(InvalidUserLoginTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse invalidUserLoginTokenExceptionHandler(InvalidUserLoginTokenException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_LOGIN_USER_INVALID_TOKEN_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_LOGIN_USER_INVALID_TOKEN_MSG);
        return errorResponse;
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse userNotExistExceptionHandler(UserNotExistException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_LOGIN_USER_NOT_EXIST_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_LOGIN_USER_NOT_EXIST_MSG);
        return errorResponse;
    }

    @ExceptionHandler(UserInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse userInvalidExceptionHandler(UserInvalidException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_LOGIN_USER_NOT_EXIST_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_LOGIN_USER_NOT_EXIST_MSG);
        return errorResponse;
    }

    @ExceptionHandler(UserOrPasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse userOrPasswordMismatchExceptionHandler(UserOrPasswordMismatchException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_LOGIN_USER_NOT_EXIST_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_LOGIN_USER_NOT_EXIST_MSG);
        return errorResponse;
    }

    @ExceptionHandler(PermissionAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomErrorResponse ermissionAlreadyExistException(PermissionAlreadyExistException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorCode(ErrorConstants.ERROR_PERMISSION_ALREADY_EXIST_CODE);
        errorResponse.setErrorMessage(ErrorConstants.ERROR_PERMISSION_ALREADY_EXIST_MSG);
        return errorResponse;
    }
}
