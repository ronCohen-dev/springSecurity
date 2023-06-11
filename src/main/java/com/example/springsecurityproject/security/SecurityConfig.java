package com.example.springsecurityproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeHttpRequests()
                .antMatchers("/","index.html","css/*","js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

        @Override
        @Bean
    protected UserDetailsService userDetailsService(){
            UserDetails ronUser = User.builder()
                    .username("ron")
                    .password(passwordEncoder.encode("password"))
                    .roles(ApplicationUserRole.STUDENT.name())
                    .build();

            UserDetails mariaUser = User.builder()
                    .username("maria")
                    .password(passwordEncoder.encode("password123"))
                    .roles(ApplicationUserRole.ADMIN.name())
                    .build();
            return new InMemoryUserDetailsManager(ronUser ,mariaUser);
        }

}

