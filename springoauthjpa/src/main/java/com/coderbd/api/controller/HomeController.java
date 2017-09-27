package com.coderbd.api.controller;

import com.coderbd.api.entity.User;
import com.coderbd.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * @apiNote Home
     * @return
     */
    @GetMapping(value = "/")
    public String index(){
        return "Hellow Coders!";
    }

    /**
     * @apiNote Save method for User
     * @param user
     */

    @PostMapping(value = "/save")
    public void saveUser(@RequestBody User user){
        if(user != null) {
            userService.saveUser(user);
        }
    }

    @GetMapping(value = "/user")
    public void getUserByUsername(@RequestParam String username){
       userService.getSngleUser(username);
    }
}
