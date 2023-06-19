package com.example.springsecurityproject.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsernameAndPasswordAuthenticationRequest{

    private String userName;

    private String password;

}
