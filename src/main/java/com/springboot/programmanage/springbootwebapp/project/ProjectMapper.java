package com.springboot.programmanage.springbootwebapp.project;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProjectMapper {


    @Select("select project.* from project")
    List<LightProject> getAll();

    @Select("select project.* from project where project.project_name=#{projectName}")
    LightProject getSingle(String projectName);

    @Update({"update project set project_name=#{projectName},",
            "project_introduction=#{projectIntroduction},",
            "is_passed=#{isPassed},",
            "file_url=#{fileUrl}",
            "where project_id=#{projectId}"})
    int updateLight(LightProject project);


    @Insert({"insert into project(project_id,project_name,project_introduction,is_passed,file_url)",
    "values(#{projectId},#{projectName},#{projectIntroduction},#{isPassed},#{fileUrl})"})
    @Options(useGeneratedKeys = true,keyProperty = "projectId")
    int insert(Project project);

    @Delete("delete from project where project_id=#{id}")
    int delete(int id);

    @Update({"update project set project_name=#{projectName},",
            "project_introduction=#{projectIntroduction},",
            "is_passed=#{isPassed},",
            "file_url=#{fileUrl}",
            "where project_id=#{projectId}"})
    int update(Project project);

    @Select({"select project.*,image.* from project",
            "left join user_project up on project.project_id=up.project_id",
            "left join user on user.user_id=up.user_id",
            "left join image_project ip on project.project_id=ip.project_id",
            "left join image on image.image_id=ip.image_id",
            "where user.user_name=#{userName}"})
    @ResultMap("com.springboot.programmanage.springbootwebapp.project.ProjectMapper.ProjectResult")
    List<Project> getProjectsByUserName(String userName);

    @Select({"select project.*,user.*,image.* from project",
    "left join user_project up on project.project_id=up.project_id",
    "left join user on user.user_id=up.user_id",
    "left join image_project ip on project.project_id=ip.project_id",
    "left join image on image.image_id=ip.image_id"})
    @ResultMap("com.springboot.programmanage.springbootwebapp.project.ProjectMapper.ProjectResult")
    List<Project> getAllProjects();

    @Select({"select project.*,user.*,image.* from project",
            "left join user_project up on project.project_id=up.project_id",
            "left join user on user.user_id=up.user_id",
            "left join image_project ip on project.project_id=ip.project_id",
            "left join image on image.image_id=ip.image_id",
            "where project.project_name=#{projectName}"})
    @ResultMap("com.springboot.programmanage.springbootwebapp.project.ProjectMapper.ProjectResult")
    Project getProjectByName(String projectName);


    @Select({"select project.*,user.*,image.* from project",
            "left join user_project up on project.project_id=up.project_id",
            "left join user on user.user_id=up.user_id",
            "left join image_project ip on project.project_id=ip.project_id",
            "left join image on image.image_id=ip.image_id",
            "where project.project_id=#{projectId}"})
    @ResultMap("com.springboot.programmanage.springbootwebapp.project.ProjectMapper.ProjectResult")
    Project getProjectById(int projectId);

}
