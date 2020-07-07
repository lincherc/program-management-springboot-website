package com.springboot.programmanage.springbootwebapp.examine;

import com.springboot.programmanage.springbootwebapp.project.Project;
import com.springboot.programmanage.springbootwebapp.project.ProjectService;
import com.springboot.programmanage.springbootwebapp.user.Account;
import com.springboot.programmanage.springbootwebapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamineController {
    private final ProjectService projectService;
    private final UserService userService;

    public ExamineController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }
    // @Autowired
    //UserProjectService userProjectService;

    @GetMapping("/pass")
    public String programIsPassed(@RequestParam(name="projectId",defaultValue = "0")int projectId,
                                  @RequestParam(name="isPassed", defaultValue = "0")int isPassed,
                                    Model model){

        //Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        //String currentCheckerName=authentication.getName();
        Project program=projectService.getProjectById(projectId);
        Account personInCharge=userService.getUserByProjectId(program.getProjectId());
        //Account checker=userService.getUser(currentCheckerName);
        //UserProject userProject=new UserProject(checker.getUserId(),program.getProjectId());

        if (isPassed == 1) {
            program.setIsPassed(1);
            if (projectService.updateProject(program)) {
                model.addAttribute("ok", "项目通过审核");
            }
        }
        else if (isPassed == -1) {
            program.setIsPassed(-1);
            if (projectService.updateProject(program)) {
                model.addAttribute("ok", "项目不合格");
            }
        }

        //userProjectService.addUserProject(userProject);
        model.addAttribute("personInCharge",personInCharge);
        model.addAttribute("projectChecked",program);
        return "customer/pages/check_confirm";
    }

}
