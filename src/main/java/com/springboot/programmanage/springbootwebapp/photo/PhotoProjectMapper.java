package com.springboot.programmanage.springbootwebapp.photo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PhotoProjectMapper {
    @Insert("insert into image_project(id,image_id,project_id) values(#{id},#{imageId},#{projectId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertPhotoProject(PhotoProject photoProject);
}
