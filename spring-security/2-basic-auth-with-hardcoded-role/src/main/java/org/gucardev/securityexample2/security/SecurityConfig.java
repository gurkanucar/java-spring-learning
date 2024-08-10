package org.gucardev.securityexample2.security;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


    private final UserDetailsServiceImpl userDetailsService;

    private static final String[] IGNORED_PATHS = {
            "/auth/**",
            "/public/**",
            "/h2-console/**"
    };

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> Arrays.stream(IGNORED_PATHS)
                .forEach(path -> web.ignoring().requestMatchers(new AntPathRequestMatcher(path)));
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable)
                // our custom user details service
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

}
