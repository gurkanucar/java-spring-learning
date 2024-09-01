package org.gucardev.springprofiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {

    @Profile("dev")
    @Bean
    public MyClass myClassDev() {
        return new MyClass("dev", "devValue");
    }

    @Profile("test")
    @Bean
    public MyClass myClassTest() {
        return new MyClass("test", "testValue");
    }

}
