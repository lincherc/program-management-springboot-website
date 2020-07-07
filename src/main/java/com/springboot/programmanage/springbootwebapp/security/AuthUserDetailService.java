package com.springboot.programmanage.springbootwebapp.security;

import com.springboot.programmanage.springbootwebapp.role.Role;
import com.springboot.programmanage.springbootwebapp.user.Account;
import com.springboot.programmanage.springbootwebapp.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class AuthUserDetailService implements UserDetailsService {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthUserDetailService.class);
    private final UserMapper userMapper;

    @Autowired
    public AuthUserDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails=null;
        try{
            Account user=userMapper.getUserByNameorEmail(username);
            if(user!=null){
                userDetails=new AuthUserDetails(user,getAuthorities(user.getRoles()));
            }else{
                throw new UsernameNotFoundException("账户不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userDetails;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles){
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        for(Role r:roles){
            String roleName=r.getName();
            //use permission instead of roles?
            LOGGER.info("assign role"+roleName+" to user");
            SimpleGrantedAuthority grant=new SimpleGrantedAuthority(roleName);
            authorities.add(grant);

        }
        return authorities;

    }

}
