package com.cmx.mall.security;


import com.cmx.mall.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/**").permitAll()

                //主页和注册页不用拦截 其余全部拦截
                .antMatchers("/","index","/user/register").permitAll()
                .antMatchers("/**").authenticated()

//                .antMatchers("/alipay_callback","/return_callback").permitAll()
//
////                .antMatchers(HttpMethod.POST).permitAll()
//                .antMatchers(HttpMethod.GET, "/tab_selectBook").permitAll()
//                //authenticated()登录认l证 hasAuthority("")权限认证 如果用户不是普通会员则不能访问
//                .antMatchers("/books/buy").hasAuthority("普通会员")
//                .antMatchers("/admin/**").hasAuthority("高级会员")
//                .antMatchers("/books/shopCart","/books/shopCart/**").authenticated()
//                .antMatchers("/order","/order/**").authenticated()
//
////                .antMatchers("/books/shopCart/add").authenticated()
//                .anyRequest().authenticated()//其他请求都需要认证
                .and()
                .formLogin()
                .loginPage("/user/login")//   /login请求不需要拦截
                .loginProcessingUrl("/user/login")
                .successHandler(new SuccessHandle())
                .failureForwardUrl("/user/login/error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")//注销
                .permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf()
                .disable()
        ;// /logout不需要拦截


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**","/layui/**","/upload/**");
    }


}
