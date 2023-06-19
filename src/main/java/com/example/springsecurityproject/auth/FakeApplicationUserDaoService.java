package com.example.springsecurityproject.auth;


import com.example.springsecurityproject.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository("demo")
public class FakeApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String userName) {
        return getApplicationUser()
                .stream()
                .filter(applicationUser -> userName.equals(applicationUser.getUsername())).findFirst();
    }

    private List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
              new ApplicationUser(
                      "ron",
                      passwordEncoder.encode("password"),
                      ApplicationUserRole.STUDENT.grantedAuthorities(),
                      true,
                      true,
                      true,
                      true

              ),
                new ApplicationUser(
                        "maria",
                        passwordEncoder.encode("password"),
                        ApplicationUserRole.ADMIN.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true

                ),
                new ApplicationUser(
                        "tal",
                        passwordEncoder.encode("password"),
                        ApplicationUserRole.ADMINTRAINEE.grantedAuthorities(),
                        true,
                        true,
                        true,
                        true

                )

        );
        return applicationUsers;
    }
}
