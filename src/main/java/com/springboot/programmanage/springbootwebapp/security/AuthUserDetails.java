package com.springboot.programmanage.springbootwebapp.security;

import com.springboot.programmanage.springbootwebapp.user.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class AuthUserDetails implements UserDetails {

    private Account user;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthUserDetails(Account user,Collection<? extends GrantedAuthority> authorities){
        super();
        this.user=user;
        this.authorities=authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;     //user.isEnabled();
    }
}