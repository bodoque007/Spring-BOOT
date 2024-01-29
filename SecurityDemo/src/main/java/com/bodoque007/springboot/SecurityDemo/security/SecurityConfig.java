package com.bodoque007.springboot.SecurityDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return userDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/denied"))
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails eren = User.builder()
                .username("eren")
                .roles("EMPLOYEE")
                .password("{noop}eren")
                .build();
        UserDetails mary = User.builder()
                .username("maryOnAcross")
                .roles("EMPLOYEE", "MANAGER")
                .password("{noop}mary")
                .build();
        UserDetails linus = User.builder()
                .username("linus")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .password("{noop}linus")
                .build();
        return new InMemoryUserDetailsManager(eren, mary, linus);
    }
     */
}
