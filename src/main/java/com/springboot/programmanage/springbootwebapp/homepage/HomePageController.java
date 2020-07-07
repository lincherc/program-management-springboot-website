package com.springboot.programmanage.springbootwebapp.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {



    @GetMapping("/home")
    public  String homePage(){
        //model.addAttribute("isClose",false);
        return "customer/pages/index";
    }

}
