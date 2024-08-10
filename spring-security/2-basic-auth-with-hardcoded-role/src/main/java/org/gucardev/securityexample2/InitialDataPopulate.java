package org.gucardev.securityexample2;

import lombok.RequiredArgsConstructor;
import org.gucardev.securityexample2.dto.UserRequest;
import org.gucardev.securityexample2.entity.Role;
import org.gucardev.securityexample2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        createDummyData();
    }

    private void createDummyData() {

        var admin = new UserRequest();
        admin.setName("admin");
        admin.setUsername("admin");
        admin.setPassword("pass");
        admin.setEnabled(true);
        admin.setRole(Role.ROLE_ADMIN);
        userService.createUser(admin);

        var user = new UserRequest();
        user.setName("user");
        user.setUsername("user");
        user.setPassword("pass");
        user.setEnabled(true);
        user.setRole(Role.ROLE_USER);
        userService.createUser(user);

        var user2 = new UserRequest();
        user2.setName("user2");
        user2.setUsername("user2");
        user2.setPassword("pass");
        user2.setRole(Role.ROLE_USER);
        userService.createUser(user2);

        var mod = new UserRequest();
        mod.setName("mod");
        mod.setUsername("mod");
        mod.setPassword("pass");
        mod.setEnabled(true);
        mod.setRole(Role.ROLE_MOD);
        userService.createUser(mod);
    }
}