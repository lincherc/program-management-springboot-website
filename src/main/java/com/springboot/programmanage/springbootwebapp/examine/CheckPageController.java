package com.springboot.programmanage.springbootwebapp.examine;

import com.springboot.programmanage.springbootwebapp.api.LocationService;
import com.springboot.programmanage.springbootwebapp.project.Project;
import com.springboot.programmanage.springbootwebapp.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class CheckPageController {

    private final ProjectService projectService;
    private final LocationService locationService;

    @Autowired
    public CheckPageController(ProjectService projectService, LocationService locationService) {
        this.projectService = projectService;
        this.locationService = locationService;
    }


    @GetMapping("/checker/checklist")
    public String checkListPage(Model model){
        List<Project> allPrograms=projectService.getAllProject();
        model.addAttribute("allPrograms",allPrograms);
        return "customer/pages/product_list";
    }

    @GetMapping("/checker/checklist/{projectId}")
    public String projectDetail(@PathVariable("projectId") int projectId,
                                Model model){
        // model.addAttribute("isClose",true);
        Project program= projectService.getProjectById(projectId);
        model.addAttribute("program",program);

        int isPassOrNot=program.getIsPassed();
        if(isPassOrNot==0) {
            return "customer/pages/single-product";
        }
        else {
            if(isPassOrNot==1){
                model.addAttribute("isPassed","项目已通过审核");
            }
            else{
                model.addAttribute("isPassed","项目未通过审核");
            }
            return "customer/pages/project_checked_details";
        }
    }
/*
    @GetMapping("/checker/projectsChecked")
    public String projectCheckedPage(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentChecker=authentication.getName();
        List<Project> associatedProjects=projectService.getAssociatedProjects(currentChecker);
        model.addAttribute("projectsChecked",associatedProjects);
        return "customer/pages/projects_checked";
    }

    @GetMapping("/checker/projectsChecked/{id}")
    public String projectCheckedDetails(@PathVariable("id") int projectId,
            Model model){
        Project program=projectService.getProjectById(projectId);

        int isPassOrNot=program.getIsPassed();
        if(isPassOrNot==1){
            model.addAttribute("isPassed","项目已通过审核");
        }
        else if(isPassOrNot==0){
            model.addAttribute("isPassed","项目待审核");
        }
        else{
            model.addAttribute("isPassed","项目未通过审核");
        }
        model.addAttribute("program",program);
        return "customer/pages/project_checked_details";

    }

 */

    @ResponseBody
    @PostMapping("/checker/projectsChecked/{id}/download")
    public String testDownload(@PathVariable("id")int projectId, HttpServletResponse response) throws IOException {

        String storehouse =locationService.getStoreHouse();
        String fileName = projectService.getProjectById(projectId).getFileUrl();
        //System.out.println(storehouse+"/file/" + fileName);

        response.setHeader("content-type", "application/octet-stream;charset=utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        byte[] buff = new byte[2048];
        //创建缓冲输入流
        FileInputStream fis=null;
        OutputStream outputStream=null;
        try {
            fis=new FileInputStream(new File(storehouse+ "/file/" + fileName));
            //这个路径为待下载文件的路径
            outputStream= response.getOutputStream();
            int read;
            //通过while循环写入到指定了的文件夹中
            while ((read=fis.read(buff)) != -1) {
                outputStream.write(buff, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                    fis.close();
            }
            if (outputStream != null) {
                    outputStream.close();
            }
        }
        return "下载可能出现问题";
    }
}

