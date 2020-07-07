package com.springboot.programmanage.springbootwebapp.project;

import com.springboot.programmanage.springbootwebapp.photo.Photo;
import com.springboot.programmanage.springbootwebapp.photo.PhotoMapper;
import com.springboot.programmanage.springbootwebapp.photo.PhotoProject;
import com.springboot.programmanage.springbootwebapp.photo.PhotoProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final PhotoMapper photoMapper;
    private final PhotoProjectMapper photoProjectMapper;
    private final UserProjectMapper userProjectMapper;

    @Autowired
    public ProjectService(ProjectMapper projectMapper, PhotoMapper photoMapper, PhotoProjectMapper photoProjectMapper, UserProjectMapper userProjectMapper) {
        this.projectMapper = projectMapper;
        this.photoMapper = photoMapper;
        this.photoProjectMapper = photoProjectMapper;
        this.userProjectMapper = userProjectMapper;
    }

    public boolean createProject(Project project){
        try{
            projectMapper.insert(project);

            UserProject userProject=new UserProject(project.getPersonInCharge().getUserId(),
                    project.getProjectId());
            userProjectMapper.insertUserProject(userProject);

            for(Photo singlePhoto:project.getProjectPhotos()) {
                photoMapper.insertPhoto(singlePhoto);
                PhotoProject photoProject=new PhotoProject(singlePhoto.getImageId(),
                        project.getProjectId());
                photoProjectMapper.insertPhotoProject(photoProject);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public boolean removeProject(int id){
        try{
            int ret= projectMapper.delete(id);
            return ret>0;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public boolean updateProject(Project project){
        try{
            int ret= projectMapper.update(project);
            return ret>0;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public List<Project> getAllProject(){
        List<Project> res=null;
        try{
            res= projectMapper.getAllProjects();
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public Project getProject(String projectName){
        Project res=null;
        try{
            res= projectMapper.getProjectByName(projectName);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public Project getProjectById(int projectId){
        Project res=null;
        try{
            res= projectMapper.getProjectById(projectId);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }


    public List<Project> getAssociatedProjects(String userName){
        List<Project> res=null;
        try{
            res= projectMapper.getProjectsByUserName(userName);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public List<LightProject> getAllLightProjects(){
        List<LightProject> res=null;
        try{
            res=projectMapper.getAll();
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public LightProject getLightProject(String projectName){
        LightProject res=null;
        try{
            res=projectMapper.getSingle(projectName);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public boolean updateLightProject(LightProject project){
        try{
            int ret= projectMapper.updateLight(project);
            return ret>0;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

}
