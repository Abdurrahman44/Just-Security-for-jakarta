package com.example.jwt_security.DTO.Request.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRequest {

    private Long id;
    private String name;

    private String lastName;

    private String email;

    private String password;


}
