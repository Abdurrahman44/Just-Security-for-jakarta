package com.example.jwt_security.Business.Abstracts;

import com.example.jwt_security.DTO.Request.User.CreateRequest;
import com.example.jwt_security.DTO.Request.User.Login;
import com.example.jwt_security.DTO.Request.User.TokenResponse;
import com.example.jwt_security.DTO.Request.User.UpdateRequest;
import com.example.jwt_security.DTO.Response.User.GetByIdUsersResponse;

public interface UsersService {
    GetByIdUsersResponse getId(Long id);

    void creatRequest(CreateRequest createRequest);

    void updateRequest(UpdateRequest updateRequest);

    void deleteUser(Long id);

    TokenResponse login(Login login);

}
