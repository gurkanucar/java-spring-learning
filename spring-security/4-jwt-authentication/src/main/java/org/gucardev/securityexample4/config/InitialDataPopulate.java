package org.gucardev.securityexample4.config;

import lombok.RequiredArgsConstructor;
import org.gucardev.securityexample4.model.Role;
import org.gucardev.securityexample4.model.User;
import org.gucardev.securityexample4.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        var user = new User();
        user.setUsername("admin");
        user.setPassword("pass");
        user.setRole(Role.ADMIN);
        user.setIsEnabled(true);
        user = userService.createUser(user);

    }

}
