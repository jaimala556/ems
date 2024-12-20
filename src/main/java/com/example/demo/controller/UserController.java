package com.example.demo.controller;

import com.example.demo.helper.UsersHelper;
import com.example.demo.service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired private UsersService usersService;
    @PostMapping("/login")
    public String login(@RequestBody UsersHelper usersHelper){
        return usersService.login(usersHelper);
    }
    @GetMapping("/forget/{email}")
    public String forget(@PathVariable String email){
        return usersService.forget(email);
    }
    @PutMapping("/reset-password")
    public String resetPassword(@RequestBody UsersService.ResetPassword resetPassword){
        return usersService.resetPassword(resetPassword);
    }
}
