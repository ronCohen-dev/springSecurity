package com.example.springsecurityproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("/", "/swagger-ui/**", "index.html", "css/*", "js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/courses", true)
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)).key("notDefaultPasswordOfSpringSecurity")
                .rememberMeParameter("remember").and().logout().logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // this lane just if .csrf().disable()
                .clearAuthentication(true)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID", "XSRF-TOKEN")
                .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ronUser = User.builder()
                .username("ron")
                .password(passwordEncoder.encode("password"))
//                .roles(ApplicationUserRole.STUDENT.name())
                .authorities(ApplicationUserRole.STUDENT.grantedAuthorities())
                .build();

        UserDetails mariaUser = User.builder()
                .username("maria")
                .password(passwordEncoder.encode("password"))
//                .roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.grantedAuthorities())
                .build();

        UserDetails talUser = User.builder()
                .username("tal")
                .password(passwordEncoder.encode("password"))
//                .roles(ApplicationUserRole.ADMINTRAINEE.name())
                .authorities(ApplicationUserRole.ADMINTRAINEE.grantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(ronUser, mariaUser, talUser);
    }

}

