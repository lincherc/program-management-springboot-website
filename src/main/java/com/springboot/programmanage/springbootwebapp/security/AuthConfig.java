package com.springboot.programmanage.springbootwebapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER= LoggerFactory.getLogger(WebSecurityConfigurerAdapter.class);
    private final UserDetailsService userDetailsService;
    private final AuthenticationProvider securityProvider;

    @Autowired
    public AuthConfig(UserDetailsService userDetailsService, AuthenticationProvider securityProvider) {
        this.userDetailsService = userDetailsService;
        this.securityProvider = securityProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(securityProvider);
    }

    //know how to protect

    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/resources/**","/webjars/**","/assets/**").permitAll()
                .antMatchers("/","/register","/forgetPwd","/resetPwd").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/pass/**").hasRole("CHECKER")
                .antMatchers("/checker/**").hasRole("CHECKER")
                .antMatchers("/applicant/**").hasRole("PETITIONER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/product/**").hasRole("CUSTOMER")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        LOGGER.info("Success handler");  // process need
                        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        if(principal!=null && principal instanceof UserDetails){
                            UserDetails user=(UserDetails)principal;
                            LOGGER.info("loginUser:"+user.getUsername());

                            // in session
                            //request.getSession().setAttribute("userDetail",user);
                            //response.sendRedirect("/");
                            String redirectUrl="/home";
                            SavedRequest savedRequest=(SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
                            if(savedRequest!=null){
                                redirectUrl=savedRequest.getRedirectUrl();
                                request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
                            }
                            response.sendRedirect(redirectUrl);
                        }
                        else{
                            LOGGER.info("principal error");
                        }
                    }
                })
                .failureHandler(new AuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
                        System.out.println("error"+authenticationException.getMessage());
                        response.sendRedirect("/login");
                    }
                })
                .permitAll()
                .and()
                .logout().logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                if(authentication!=null){
                    System.out.println(authentication.getName());
               /*     Cookie cookie=new Cookie("logout","ok");
                    cookie.setPath("/");
                    cookie.setMaxAge(3600);
                    response.addCookie();
                    
                */
                }
                response.setStatus(HttpStatus.OK.value());
                response.sendRedirect("/home");
                response.getWriter().flush();
            }
        })
                .permitAll()
                .and()
                .csrf().disable();     //暂时禁用csrf，否则无法提交表单

    }
}
