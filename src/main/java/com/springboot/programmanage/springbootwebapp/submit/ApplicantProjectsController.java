package com.springboot.programmanage.springbootwebapp.submit;

import com.springboot.programmanage.springbootwebapp.project.Project;
import com.springboot.programmanage.springbootwebapp.project.ProjectService;
import com.springboot.programmanage.springbootwebapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ApplicantProjectsController {


    private final ProjectService projectService;

    @Autowired
    public ApplicantProjectsController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
    }

    @GetMapping("/applicant/projects")
    public String applicantProjects(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=authentication.getName();
        List<Project> applicantProjects=projectService.getAssociatedProjects(currentUserName);
        model.addAttribute("applicantProjects",applicantProjects);
        return "customer/pages/applicant_projects";
    }

    @GetMapping("/applicant/projects/{projectId}")
    public String projectDetail(@PathVariable("projectId") int projectId,
                                Model model){
        // model.addAttribute("isClose",true);
        Project program= projectService.getProjectById(projectId);
        int isPassOrNot=program.getIsPassed();
        if(isPassOrNot==1){
            model.addAttribute("isPassed","恭喜，项目已通过审核");
        }
        else if(isPassOrNot==0){
            model.addAttribute("isPassed","项目审核中");
        }
        else{
            model.addAttribute("isPassed","很遗憾，项目未通过审核");
        }
        model.addAttribute("program",program);
        return "customer/pages/applicant_project_details";
    }
}
