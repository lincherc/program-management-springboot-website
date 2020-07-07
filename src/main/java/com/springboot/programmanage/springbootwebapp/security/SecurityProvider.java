package com.springboot.programmanage.springbootwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityProvider")
public class SecurityProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token=
                (UsernamePasswordAuthenticationToken) authentication;
        String username=token.getName();
        UserDetails userDetails=null;

        if(username!=null){
            userDetails=userDetailsService.loadUserByUsername(username);
        }

        if(userDetails==null){
            throw new UsernameNotFoundException("invalid username/password");
        }
        else if(!userDetails.isEnabled()){
            System.out.println("账户已被禁用");
            throw new DisabledException("账户已被禁用");
        }else if(!userDetails.isAccountNonExpired()){
            System.out.println(" 账户已过期");
            throw new LockedException(("账户已过期"));
        }else if(!userDetails.isAccountNonLocked()){
            System.out.println("账户已锁定");
            throw new LockedException("账户已锁定");
        }else if(!userDetails.isCredentialsNonExpired()){
            System.out.println("凭证已过期");
            throw new LockedException("凭证已过期");
        }

        String password=userDetails.getPassword();

        if(!password.equals(token.getCredentials())){
            throw  new BadCredentialsException("Invalid username/password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
