package com.springboot.programmanage.springbootwebapp.api;

import com.springboot.programmanage.springbootwebapp.project.LightProject;
import com.springboot.programmanage.springbootwebapp.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<LightProject> getAllProjects() {
        return projectService.getAllLightProjects();
    }

    /*
    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project newProject(@ModelAttribute Project program) {
        projectService.createProject(program);
        return projectService.getProjectForAdmin(program.getProjectName());
    }

     */


    @PostMapping("/projects/delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeProject(@ModelAttribute LightProject program) {
        return projectService.removeProject(program.getProjectId());
    }


    @PutMapping("/projects")
    public LightProject updateProject(@ModelAttribute LightProject program) {
        //System.out.println(user);
        if(projectService.updateLightProject(program))
            return projectService.getLightProject(program.getProjectName());
        else
            return null;
    }


/*
    @PostMapping("/cart")
    public boolean addToCart() {
        return true;
    }

 */
}
