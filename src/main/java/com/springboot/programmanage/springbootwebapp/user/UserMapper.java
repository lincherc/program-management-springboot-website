package com.springboot.programmanage.springbootwebapp.user;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user(user_id,user_name,avatar,email,password) values(#{userId},#{userName},#{avatar},#{email},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    int insert(Account user);

    @Update("update user set user_id=#{userId},user_name=#{userName},avatar=#{avatar},email=#{email},password=#{password} where user_id=#{userId}")
    int updateUser(Account user);

    @Update("update user set user_id=#{userId},user_name=#{userName},avatar=#{avatar},email=#{email},password=#{password} where user_id=#{userId}")
    int updateLightUser(LightUser user);

    @Delete("delete from user where user_id=#{id}")
    int delete(int id);


    @Select("select user.* from user")
    @ResultMap("com.springboot.programmanage.springbootwebapp.user.UserMapper.UserResultWithRoles")
    List<Account> getAllUsers();

  /*
   ,role.*
   left join user_role ur on user.user_id=ur.user_id left join role on ur.role_id=role.role_id

   @Results({
            @Result(id = true,column = "user_id",property = "userId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password")
            @Result(column = "reset_token",property ="resetToken"),
            @Result(column = "enable",property = "enable"),
    })


*/

    @Select({"select * from user",
            "join user_project up on user.user_id=up.user_id",
            "join project p on up.project_id=p.project_id",
            "where p.project_id=#{projectId}"})
    Account getUserById(int projectId);

    //get by user name
    @Select("select * from user where user_name=#{userName}")
    Account getUserByName(String userName);

    @Select({"select user.*,ur.role_id,r.name,r.description from user",
            "left join user_role ur on user.user_id=ur.user_id",
            "left join role r on ur.role_id=r.role_id",
            "where user.email=#{emailorUserName} or user.user_name=#{emailorUserName}"
    })
    @ResultMap("com.springboot.programmanage.springbootwebapp.user.UserMapper.UserResultWithRoles")
    Account getUserByNameorEmail(String emailorUserName);
    /*
           "select u.*,ur.role_id,r.name,r.description from",
            "(select * from user where email=#{emailorUserName} or user_name=#{emailorUserName})",
            "left join user_role ur on u.user_id=ur.user_id",
            "left join role r on ur.role_id=r.role_id"
            */
}
