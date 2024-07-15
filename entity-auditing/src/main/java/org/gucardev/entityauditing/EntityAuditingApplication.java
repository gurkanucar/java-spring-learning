package org.gucardev.entityauditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EntityAuditingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityAuditingApplication.class, args);
    }

}
