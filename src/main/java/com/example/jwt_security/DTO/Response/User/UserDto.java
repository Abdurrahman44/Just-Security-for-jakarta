package com.example.jwt_security.DTO.Response.User;

import com.example.jwt_security.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String Email;
    private Role role;
}
