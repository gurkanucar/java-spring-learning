package org.gucardev.authmicro;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "app.init-data")
@Getter
@Setter
public class InitDataConfig {
    private List<Role> roles;
    private List<User> users;

    @Getter
    @Setter
    public static class Role {
        private String name;
    }

    @Getter
    @Setter
    public static class User {
        private String username;
        private String password;
        private List<String> roles;
    }
}
