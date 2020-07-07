package com.springboot.programmanage.springbootwebapp.role;

import com.springboot.programmanage.springbootwebapp.user.Account;
import java.util.List;

public class Role {
    private int id;
    private String name;
    private String description;
    private List<Account> users;
   // private List<Permission> permissions;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }
/*
    @Override
    public String toString(){
        return "Role [id=" +id"]";
    }

     */

    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(obj==this)
            return true;
        if(!(obj instanceof Role))
            return false;

        Role targetRole=(Role) obj;
        if(targetRole.name.equals(this.name))
            return true;
        else
            return false;
    }
}