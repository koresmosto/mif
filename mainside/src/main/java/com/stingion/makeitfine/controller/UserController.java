package com.stingion.makeitfine.controller;

import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("userManagement")
    public ModelAndView getIndexPage() {
        return new ModelAndView("userManagement");
    }

    @GetMapping("")
    public List<User> listAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") int id) {
        return userService.findById(id);
    }
}
