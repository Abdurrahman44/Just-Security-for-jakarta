package com.example.jwt_security.DTO.Request.User;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRequest implements Serializable {//Gerekli olan Kullanıcıların özellikleri

    private String name;
    private String lastName;

    private String email;
    private String password;

}
