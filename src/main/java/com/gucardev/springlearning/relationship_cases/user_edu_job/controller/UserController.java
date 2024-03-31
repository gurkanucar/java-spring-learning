package com.gucardev.springlearning.relationship_cases.user_edu_job.controller;

import com.gucardev.springlearning.relationship_cases.user_edu_job.dto.UserDTO;
import com.gucardev.springlearning.relationship_cases.user_edu_job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user_edu_jobs")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }
}
