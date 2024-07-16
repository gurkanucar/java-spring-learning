package org.gucardev.authmicro;


import lombok.RequiredArgsConstructor;
import org.gucardev.authmicro.model.Role;
import org.gucardev.authmicro.model.User;
import org.gucardev.authmicro.service.RoleService;
import org.gucardev.authmicro.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) {
        createDummyData();
    }

    private void createDummyData() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole = roleService.createRole(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        userRole = roleService.createRole(userRole);

        Role modRole = new Role();
        modRole.setName("MODERATOR");
        modRole = roleService.createRole(modRole);

        Role serviceRole = new Role();
        serviceRole.setName("SERVICE");
        serviceRole = roleService.createRole(serviceRole);

        User admin = new User();
        admin.setName("admin");
        admin.setUsername("admin");
        admin.setPassword("pass");
        admin.setEnabled(true);
        admin.setRoles(Set.of(adminRole, modRole));
        userService.createUser(admin);

        User user = new User();
        user.setName("user");
        user.setUsername("user");
        user.setPassword("pass");
        user.setEnabled(true);
        user.setRoles(Set.of(userRole));
        userService.createUser(user);

        // Create Service Users

        User projectMicro = new User();
        projectMicro.setName("project-micro");
        projectMicro.setUsername("project-micro");
        projectMicro.setPassword("pass");
        projectMicro.setEnabled(true);
        projectMicro.setRoles(Set.of(serviceRole));
        userService.createUser(projectMicro);
    }
}
