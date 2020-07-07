package com.springboot.programmanage.springbootwebapp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  //  @Value("${host.name}")
 //   private String hostName;

    @GetMapping("/users")
    public String displayUsers(Model model){
        return "admin/pages/tables";
    }

    @GetMapping("/dashboard")
    public String displayDashboard(Model model){
        return "admin/pages/tables_projects";
    }

}
