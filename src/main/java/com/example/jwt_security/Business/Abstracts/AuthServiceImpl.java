package com.example.jwt_security.Business.Abstracts;


import com.example.jwt_security.DataAccess.UserRepository;
import com.example.jwt_security.Entities.Role;
import com.example.jwt_security.Entities.Users;
import com.example.jwt_security.config.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final PasswordEncoder encoder;
    private final TokenManager tokenManager;
    private final AuthenticationManager authManager;
    private final UserRepository repository;

    public Users createuser(Users dto) {

        Role role = new Role();
        //role.setName("ADMIN");

        Users user = new Users();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(Set.of(role));
        var newUser = repository.save(user);

        log.info("Create User: " + newUser.getId());
        return newUser;
    }

   /* public TokenResponse authenticate(Login dto) {
        var user = repository.findByEmail(dto.getEmail()).orElseThrow();

        var token = tokenManager.generateToken(user);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        log.info("Generate Token: " + dto.getEmail());
        return TokenResponse.builder().token(token).username(dto.getEmail()).name(user.getName()).surname(user.getLastName()).build();
    }
*/
}