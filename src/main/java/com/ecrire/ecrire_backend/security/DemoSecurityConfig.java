package com.ecrire.ecrire_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


    //this is filter chain for authorization (authority) which sets api requests to authorities
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
        //csrf is not required for REST api, if we are using webbased then we will use this
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    //for returning user details for authentication and also user roles for authorization
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, active from user where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user.username, role.roles from role " +
                " inner join user on user.userid = role.user_id" +
                " where username=?");
        return jdbcUserDetailsManager;
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("USER")
                .build();
        return  new InMemoryUserDetailsManager(john);
    }
    */
}