package com.springboot.programmanage.springbootwebapp.user;


import com.springboot.programmanage.springbootwebapp.role.Role;
import com.springboot.programmanage.springbootwebapp.role.RoleMapper;
import com.springboot.programmanage.springbootwebapp.role.UserRole;
import com.springboot.programmanage.springbootwebapp.role.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserService(UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
    }

    public boolean registerUser(Account user){
        try{//if dupliacte?
            user.setAvatar("项目负责人");
            int ret2=userMapper.insert(user);
            if(ret2>0) {
                UserRole userRole = new UserRole(user.getUserId(), 2);
                int ret1=userRoleMapper.insertUserRole(userRole);
                return ret1>0;
            }
            else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }



    public boolean createChecker(Account user){
        try{
            user.setAvatar("审核员");
            int ret2=userMapper.insert(user);
            if(ret2>0){
                UserRole userRole=new UserRole(user.getUserId(),1);
                int ret1=userRoleMapper.insertUserRole(userRole);
                return ret1>0;
            }
           else{
               return false;
           }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }


    public Account getUserByProjectId(int projectId){
        Account res=null;
        try{
            res=userMapper.getUserById(projectId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


    public Account getUser(String userName){
        Account res=null;
        try{
            res=userMapper.getUserByName(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateUser(Account user){
        try{
            int ret=userMapper.updateUser(user);
            return ret>0;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException((e.getCause()));
        }
    }

    public boolean removeUser(int id){
        try{
            int ret=userMapper.delete(id);
            return ret>0;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException((e.getCause()));
        }
    }

    public List<Account> getAllUsers(){
            List<Account> res=null;
            try{
                res=userMapper.getAllUsers();
            }catch (Exception e){
                e.printStackTrace();
            }
            return res;
    }


    public boolean updateLight(LightUser user){
        try{
            int ret=userMapper.updateLightUser(user);
            return ret>0;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException((e.getCause()));
        }
    }

    public List<Account> getAllUsersForCheck() {
        List<Account> resRow = null;
        List<Account> res = new ArrayList<>();
        try {
            resRow = userMapper.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Account user : resRow) {
            Account userOrCheck =userMapper.getUserByNameorEmail(user.getUserName());

            Role admin= roleMapper.selectRoleById(3);
            Role checker=roleMapper.selectRoleById(1);

            if (userOrCheck.getRoles()!=null&&!userOrCheck.getRoles().contains(admin)) {
                if (userOrCheck.getRoles().contains(checker)) {
                    userOrCheck.setAvatar("审核员");
                    //userOrCheck.setRoles(null);
                } else {
                    userOrCheck.setAvatar("项目负责人");
                    //userOrCheck.setRoles(null);
                }
                res.add(userOrCheck);
            }
        }

        return res;
    }
}
