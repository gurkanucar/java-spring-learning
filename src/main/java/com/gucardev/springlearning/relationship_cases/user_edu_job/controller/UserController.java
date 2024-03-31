package com.gucardev.springlearning.relationship_cases.user_edu_job.controller;

import com.gucardev.springlearning.relationship_cases.user_edu_job.dto.UserDTO;
import com.gucardev.springlearning.relationship_cases.user_edu_job.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user_edu_jobs")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        var user = userService.createUser(userDTO);
        log.info("user created with id {}", user.getId());
        return user;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }
}
