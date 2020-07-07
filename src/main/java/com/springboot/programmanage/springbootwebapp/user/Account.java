package com.springboot.programmanage.springbootwebapp.user;


import com.springboot.programmanage.springbootwebapp.role.Role;
import java.util.ArrayList;
import java.util.List;

/*
   SQL=>data=>onject
 */
public class Account {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String avatar;
    private List<Role> roles;

    public Account(){
        this.roles=new ArrayList<>();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(obj==this)
            return true;
        if(!(obj instanceof Account))
            return false;

        Account targetAccount=(Account) obj;
        if(targetAccount.email.equals(this.email)||targetAccount.userName.equals(this.userName))
            return true;
        else
            return false;
    }

}
