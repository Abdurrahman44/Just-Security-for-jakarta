package com.example.jwt_security.DTO.Request.User;

import com.example.jwt_security.Entities.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TokenResponse {

    private String name;
    private String surname;
    private String username;
    private String token;
    private Set<Role> role;



}