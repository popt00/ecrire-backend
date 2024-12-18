package com.ecrire.ecrire_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET,"/entry/**")
                        .hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/entry/**")
                        .hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/entry/**")
                        .hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,"/entry/**")
                        .hasRole("USER")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable() );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("USER")
                .build();
        return  new InMemoryUserDetailsManager(john);
    }
}
