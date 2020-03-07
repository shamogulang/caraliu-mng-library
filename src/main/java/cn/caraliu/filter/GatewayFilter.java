package cn.caraliu.filter;

import cn.caraliu.common.AuthorizeUser;
import cn.caraliu.common.utils.HeaderUtils;
import cn.caraliu.common.utils.JwtUtils;
import cn.caraliu.exception.InvalidUserLoginTokenException;
import cn.caraliu.exception.LoginUserExpireTokenException;
import cn.caraliu.exception.RejectAccessException;
import cn.caraliu.exception.UserNotExistException;
import cn.caraliu.mybatis.domain.user.MngPermissionEntity;
import cn.caraliu.mybatis.domain.user.MngUserEntity;
import cn.caraliu.mybatis.mapper.user.MngPermissionMapper;
import cn.caraliu.mybatis.mapper.user.MngUserMapper;
import cn.caraliu.user.info.LoginUserTokenInfo;
import cn.caraliu.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class GatewayFilter extends OncePerRequestFilter {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MngUserMapper mngUserMapper;
    @Autowired
    private MngPermissionMapper mngPermissionMapper;

    private static final List<AntPathRequestMatcher> annoPathRequestMatcherList = new ArrayList<AntPathRequestMatcher>() {
        {
            //内部接口和登录接口，放行
            this.add(new AntPathRequestMatcher("/api/mng/v*/login", HttpMethod.POST.name()));
            this.add(new AntPathRequestMatcher("/internal/**"));
        }
    };


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 是否需要jwt校验
        boolean checkJwt = true;
        // 前后端分离跨域允许设置
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, Content-Type, Accept");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }

        if (checkNotBlockApi(request)) {
            checkJwt = false;
        }

        // jwt校验
        if (checkJwt) {
            checkAuth(request);
        }

        // 设置用户的权限
        setUserPermission(request);

        filterChain.doFilter(request, response);
    }

    private void setUserPermission(HttpServletRequest request) {
        String userPkStr = HeaderUtils.getRequestHeaderValue(request, HeaderUtils.X_CARALIU_USER_PK);
        long userPk = Long.parseLong(userPkStr == null ? "0" : userPkStr);
        if (userPk > 0L) {
            MngUserEntity mngUserEntity = mngUserMapper.findOne(userPk);
            if (mngUserEntity == null) {
                throw new UserNotExistException();
            }

            List<MngPermissionEntity> mngPermissionEntities = null;

            if (mngUserEntity.getType() == MngUserEntity.Type.MNG.ordinal()) {
                mngPermissionEntities = mngPermissionMapper.findAdminPermission();
            } else {
                mngPermissionEntities = mngUserMapper.findPermissionByUserPk(userPk);
            }

            if (mngPermissionEntities != null && !mngPermissionEntities.isEmpty()) {
                List<GrantedAuthority> authorities = null;
                StringBuffer permissionNames = new StringBuffer();
                mngPermissionEntities.forEach(x -> {
                    if (permissionNames.length() == 0) {
                        permissionNames.append(x.getName());
                    } else {
                        permissionNames.append("," + x.getName());
                    }
                });
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(permissionNames.toString());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new AuthorizeUser(userPk, authorities), (Object) null, authorities);
                authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }

    private boolean checkNotBlockApi(HttpServletRequest request) {
        Iterator var2 = annoPathRequestMatcherList.iterator();
        while (var2.hasNext()) {
            AntPathRequestMatcher antPathRequestMatcher = (AntPathRequestMatcher) var2.next();
            if (antPathRequestMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    private Long checkAuth(HttpServletRequest request)  {
        String userPkStr = HeaderUtils.getRequestHeaderValue(request, HeaderUtils.X_CARALIU_USER_PK);
        long userPk = (userPkStr == null || "".equals(userPkStr)) ? 0L : Long.valueOf(userPkStr);
        // jwt token 验证
        String userToken = JwtUtils.validateUserAndReturnToken(request);
        if(StringUtils.hasText(userToken)){
            // 检验是否已经登录,或者登录是否失效
            this.checkToken(userToken, userPk);
        }else {
            throw new LoginUserExpireTokenException();
        }
        return userPk;
    }

    /**
     * @param token
     * @param userPk
     */
    private void checkToken(String token, long userPk) {
        LoginUserTokenInfo loginUserTokenInfo = loginService.getUserTokenInfo(userPk);
        if (loginUserTokenInfo == null) {
            throw new LoginUserExpireTokenException();
        } else if (userPk == loginUserTokenInfo.getUserPk() &&
                token.equals(loginUserTokenInfo.getToken())) {
            //更新缓存，防止登录过期
            loginService.updateUserLoginToken(userPk);
        } else {
            throw new InvalidUserLoginTokenException();
        }
    }
}
