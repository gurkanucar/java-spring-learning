package org.gucardev.securityexample3;

import lombok.RequiredArgsConstructor;
import org.gucardev.securityexample3.dto.RoleDto;
import org.gucardev.securityexample3.dto.UserRequest;
import org.gucardev.securityexample3.service.RoleService;
import org.gucardev.securityexample3.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) {
        createDummyData();
    }

    private void createDummyData() {

        RoleDto adminRole = new RoleDto();
        adminRole.setName("ADMIN");
        adminRole = roleService.createRole(adminRole);

        RoleDto userRole = new RoleDto();
        userRole.setName("USER");
        userRole = roleService.createRole(userRole);

        RoleDto modRole = new RoleDto();
        modRole.setName("MODERATOR");
        modRole = roleService.createRole(modRole);


        var admin = new UserRequest();
        admin.setName("admin");
        admin.setUsername("admin");
        admin.setPassword("pass");
        admin.setEnabled(true);
        admin.setRoles(Set.of(adminRole, modRole));
        userService.createUser(admin);

        var user = new UserRequest();
        user.setName("user");
        user.setUsername("user");
        user.setPassword("pass");
        user.setEnabled(true);
        user.setRoles(Set.of(userRole));
        userService.createUser(user);

        var user2 = new UserRequest();
        user2.setName("user2");
        user2.setUsername("user2");
        user2.setPassword("pass");
        user2.setRoles(Set.of(userRole));
        userService.createUser(user2);

        var mod = new UserRequest();
        mod.setName("mod");
        mod.setUsername("mod");
        mod.setPassword("pass");
        mod.setEnabled(true);
        mod.setRoles(Set.of(modRole));
        userService.createUser(mod);
    }
}