package com.springboot.programmanage.springbootwebapp.role;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Insert("insert into role(role_id,name,description) values(#{id},#{name},#{description})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertRole(Role role);

    @Select("select role.* from role where role_id=#{id}")
    Role selectRoleById(int id);
}
