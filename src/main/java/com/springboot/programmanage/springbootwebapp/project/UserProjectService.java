package com.springboot.programmanage.springbootwebapp.project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjectService {

    private final UserProjectMapper userProjectMapper;

    public UserProjectService(UserProjectMapper userProjectMapper) {
        this.userProjectMapper = userProjectMapper;
    }

    public boolean addUserProject(UserProject userProject){
        try{
            int ret=userProjectMapper.insertUserProject(userProject);
            return ret>0;

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}
