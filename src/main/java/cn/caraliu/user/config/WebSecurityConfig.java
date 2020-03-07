package cn.caraliu.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring Security 的配置信息
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
          //表明需要授权
          httpSecurity.authorizeRequests()
                  //直接放行（路径+权限）
                  .antMatchers("/**").permitAll()
                  //任何请求认证后才能访问
                  .anyRequest().authenticated()
                  //表示csrf拦截失效
                  .and().csrf().disable();
    }
}
