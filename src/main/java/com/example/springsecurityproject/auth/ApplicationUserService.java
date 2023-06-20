package com.example.springsecurityproject.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {


    private final ApplicationUserDao applicationUserDao;

    public ApplicationUserService(@Qualifier("demo") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return applicationUserDao.selectApplicationUserByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user %s not found",userName)));
    }
}