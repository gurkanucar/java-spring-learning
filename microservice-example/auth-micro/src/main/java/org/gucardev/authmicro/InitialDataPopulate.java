package org.gucardev.authmicro;


import lombok.RequiredArgsConstructor;
import org.gucardev.authmicro.model.Role;
import org.gucardev.authmicro.model.User;
import org.gucardev.authmicro.service.RoleService;
import org.gucardev.authmicro.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InitialDataPopulate implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final InitDataConfig initDataConfig;

    @Override
    public void run(String... args) {
        createDummyData();
    }

    private void createDummyData() {
        initDataConfig.getRoles().forEach(r -> {
            Role role = new Role();
            role.setName(r.getName());
            roleService.createRole(role);
        });

        initDataConfig.getUsers().forEach(u -> {
            User user = new User();
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setEnabled(true);
            Set<Role> userRoles = u.getRoles().stream()
                    .map(roleService::findByName)
                    .collect(Collectors.toSet());
            user.setRoles(userRoles);
            userService.createUser(user);
        });
    }
}
