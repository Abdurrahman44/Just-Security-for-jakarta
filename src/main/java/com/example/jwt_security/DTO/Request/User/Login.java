package com.example.jwt_security.DTO.Request.User;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {

    private String email;
    private String password;
}
