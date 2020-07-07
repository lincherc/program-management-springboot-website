package com.springboot.programmanage.springbootwebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/***
 *
 * for login and register
 *
 * ***/
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
    public String oldStyleRegisterUser(HttpServletRequest request, @ModelAttribute Account user, RedirectAttributes redirectAttributes,Model model){


        List<Account> allAccounts=userService.getAllUsers();
      /*  if(allAccounts!=null){
            model.addAttribute("accounts",allAccounts);
        }
       */


        if (request.getMethod().equals("GET")) {
            return "admin/pages/register";
        }else if(request.getMethod().equals("POST")){
            if(user!=null&&user.getUserName()!=null&&!user.getUserName().isEmpty()) {
                if(allAccounts!=null){
                    if(allAccounts.contains(user)) {
                        model.addAttribute("errorMessage", "用户名已被注册");
                    }else{
                        userService.registerUser(user);

                        redirectAttributes.addFlashAttribute("successMessage","账号注册成功");
                        return "redirect:/login";
                    }
                }
            }else{
                model.addAttribute("errorMessage","请输入合理内容");
            }
            return "admin/pages/register";
        }
        return "admin/pages/register";
    }

    @GetMapping("/cookie")
    @ResponseBody
    public String createCookie(HttpServletResponse response,Model model){
        Cookie newCookie=new Cookie("testCookie","testCookieValue");
        newCookie.setMaxAge(24*60*60);
        response.addCookie(newCookie);
        return "good cookie";
    }


    @GetMapping("/login")
    public String getLoginForm(){
        return "admin/pages/login";
    }

    /*
    @GetMapping("/profile")
    @ResponseBody
    public Account getUserProfile(Model model){
        return userService.getUser("joshua");
    }


     */
}