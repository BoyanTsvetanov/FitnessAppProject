package com.example.FitnessAppProject.config;

import com.example.FitnessAppProject.repo.UserRepository;
import com.example.FitnessAppProject.service.secure.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/login", "/register", "/login-error").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin.loginPage("/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/login-error");
                }
        ).logout(
              logout -> {
                  logout.logoutUrl("/logout")
                          .logoutSuccessUrl("/")
                          .invalidateHttpSession(true);
              }
        ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return  new UserDetailsServiceImpl(userRepository);
    }


}
