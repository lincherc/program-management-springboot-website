package com.springboot.programmanage.springbootwebapp.api;

import com.springboot.programmanage.springbootwebapp.user.Account;
import com.springboot.programmanage.springbootwebapp.user.LightUser;
import com.springboot.programmanage.springbootwebapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OpenController {

    private final UserService userService;

    public OpenController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Account> getAllUsers() {
        return userService.getAllUsersForCheck();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createUser(@ModelAttribute Account user) {
        if(userService.createChecker(user))
            return userService.getUser(user.getUserName());
        else
            return null;
    }

    @PostMapping("/users/delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeUser(@ModelAttribute LightUser user) {
        return userService.removeUser(user.getUserId());
    }

    @PutMapping("/users")
    public Account updateUser(@ModelAttribute LightUser user) {
        //System.out.println(user);
        userService.updateLight(user);
        return userService.getUser(user.getUserName());
    }
/*
    @PostMapping("/cart")
    public boolean addToCart() {
        return true;
    }

 */
}
