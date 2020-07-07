package com.springboot.programmanage.springbootwebapp.submit;

import com.springboot.programmanage.springbootwebapp.api.EdgeIEPath;
import com.springboot.programmanage.springbootwebapp.api.LocationService;
import com.springboot.programmanage.springbootwebapp.photo.Photo;
import com.springboot.programmanage.springbootwebapp.project.Project;
import com.springboot.programmanage.springbootwebapp.project.ProjectService;
import com.springboot.programmanage.springbootwebapp.user.Account;
import com.springboot.programmanage.springbootwebapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SubmitController {
    private final ProjectService projectService;
    private final UserService userService;
    private final EdgeIEPath edgeIEPath;
    private final LocationService locationService;

    @Autowired
    public SubmitController(ProjectService projectService, UserService userService, EdgeIEPath edgeIEPath, LocationService locationService) {
        this.projectService = projectService;
        this.userService = userService;
        this.edgeIEPath = edgeIEPath;
        this.locationService = locationService;
    }

    @GetMapping("/applicant/submit")
    public String applicantSubmit(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentUserName=authentication.getName();
        Account currentUser=userService.getUser(currentUserName);
        model.addAttribute("currentUser",currentUser);
        return "customer/pages/checkout";
    }


    @PostMapping("/applicant/submit/upload")
    public String upload(@RequestParam("programName") String programName,
                         @RequestParam("programIntroduction") String programIntroduction,
                         @RequestParam("programImage") MultipartFile[] programPhotos,
                         @RequestParam("programFile") MultipartFile programFile,
                         Model model) throws IOException {
        boolean flag;
        if (programPhotos.length!=0 && !programFile.isEmpty()) {
            /* 获取上传的文件名称，并结合存放路径，构建新的文件名称
            String suffixName = filename.substring(filename.lastIndexOf("."));
            String suffixName1 = filename1.substring(filename1.lastIndexOf("."));
            String suffixName2 = filename2.substring(filename2.lastIndexOf("."));
            System.out.println(suffixName);
            System.out.println(suffixName1);
            System.out.println(suffixName2);
            if (false) {        //!suffixName.equals(".jpg") && !suffixName.equals(".png")
                //  attributes.addFlashAttribute("message", "图片格式只能是jpg或png！");
                // flag = false;
            } else if (false) {//!suffixName1.equals(".mp4") && !suffixName1.equals(".mkv")
                attributes.addFlashAttribute("message", "视频格式只能是mp4或mkv！");
                flag = false;
            } else if (false) {//!suffixName2.equals(".mp3") && !suffixName2.equals(".flac")
                attributes.addFlashAttribute("message", "视频格式只能是flac或mp3！");
                flag = false;
               } else {
 */
            // 构建上传文件的存放路径
            String pathRow =locationService.getStoreHouse();
            List<String> imageNames=new ArrayList<>();
            for (MultipartFile programPhoto : programPhotos) {
                MultipartFile singlePhoto=programPhoto;
                String photoNameRow = singlePhoto.getOriginalFilename();
                String photoName=edgeIEPath.pathChange(photoNameRow);
                imageNames.add(photoName);
                File imageUrl=new File(pathRow + "/image/"+photoName);

                if (!imageUrl.getParentFile().exists()) {
                    imageUrl.getParentFile().mkdirs();
                }
                //System.out.println(imageUrl);
                singlePhoto.transferTo(imageUrl);
            }

            String fileNameRow = programFile.getOriginalFilename();
            String fileName=edgeIEPath.pathChange(fileNameRow);
            File fileUrl = new File(pathRow + "/file/"+fileName);
            if (!fileUrl.getParentFile().exists()) {
                fileUrl.getParentFile().mkdirs();
            }
            //System.out.println(fileUrl);
            programFile.transferTo(fileUrl);

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String personInChargeName=authentication.getName();

            Account personInCharge=userService.getUser(personInChargeName);
            List<Photo> projectPhotos=new ArrayList<>();
            for(String imageName:imageNames){
                Photo projectPhoto=new Photo(imageName);
                projectPhotos.add(projectPhoto);
            }

            Project program= new Project
                    (programName,programIntroduction,fileName,
                            personInCharge,projectPhotos);
            if(projectService.createProject(program)) {
                model.addAttribute("ok", "提交成功,等待审核");
                model.addAttribute("projectUploaded", program);
                model.addAttribute("personInCharge", personInCharge);
            }
        }

        return "customer/pages/confirmation";
    }

}

