package com.springboot.programmanage.springbootwebapp.role;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserRoleMapper {

    @Insert("insert into user_role(id,user_id,role_id) values(#{id},#{userId},#{roleId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertUserRole(UserRole userRole);
}
