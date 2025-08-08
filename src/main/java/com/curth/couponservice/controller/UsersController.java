package com.curth.couponservice.controller;

import com.curth.couponservice.model.Users;
import com.curth.couponservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/add")
    public Users addUser(@RequestBody Users user) {
        return userDetailsService.addUser(user);
    }

    @GetMapping("/all")
    public List<Users> getUsers() {
        return userDetailsService.getUsers();
    }
}
