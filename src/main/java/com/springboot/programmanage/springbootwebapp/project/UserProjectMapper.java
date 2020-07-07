package com.springboot.programmanage.springbootwebapp.project;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserProjectMapper {
    @Insert("insert into user_project(id,user_id,project_id) values(#{id},#{userId},#{projectId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertUserProject(UserProject userProject);
}
