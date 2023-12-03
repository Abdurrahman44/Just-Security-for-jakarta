package com.example.jwt_security.Business.concret;


import com.example.jwt_security.Business.Abstracts.RolService;
import com.example.jwt_security.Business.Abstracts.UsersService;
import com.example.jwt_security.DTO.Request.User.CreateRequest;
import com.example.jwt_security.DTO.Request.User.Login;
import com.example.jwt_security.DTO.Request.User.TokenResponse;
import com.example.jwt_security.DTO.Request.User.UpdateRequest;
import com.example.jwt_security.DTO.Response.User.GetAllUsersResponse;
import com.example.jwt_security.DTO.Response.User.GetByIdUsersResponse;
import com.example.jwt_security.DataAccess.RoleRepository;
import com.example.jwt_security.DataAccess.UserRepository;
import com.example.jwt_security.Entities.Role;
import com.example.jwt_security.Entities.Users;
import com.example.jwt_security.config.TokenManager;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service//Kullanici Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    // private final ModelMapperService modelMapperService;
    private RoleRepository roleRepository;
    private RolService service;
    private final TokenManager tokenManager;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper, RolService service, TokenManager tokenManager) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.service = service;
        this.roleRepository = roleRepository;
        this.tokenManager = tokenManager;
    }


    public List<GetAllUsersResponse> getAll() {//it is work
        List<Users> Users = userRepository.findAll();
        return Users.stream().map(user -> mapper.map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetByIdUsersResponse getId(Long id) {//it is work
        Users users = userRepository.findById(id).orElseThrow(() -> new EntityExistsException("User not found"));

        return mapper.map(users, GetByIdUsersResponse.class);
    }


    @Override//it is work
    public void creatRequest(CreateRequest createRequest) {
        Users users = mapper.map(createRequest, Users.class);
        users.setPassword(passwordEncoder.encode(createRequest.getPassword()));
        Set<Role> userRoles = new HashSet<>();

        Role existingRole = roleRepository.findByRoleName("USER");
        userRoles.add(existingRole);
        users.setRoles(userRoles);
        this.userRepository.save(users);
        //     for (Role role : createRequest.getRoles()) {
//         Role existingRole = roleRepository.findByRoleName("USER");
//           if (existingRole != null) {
//                userRoles.add(existingRole);
//           }
//        }
    }


    @Override
    public void updateRequest(UpdateRequest updateRequest) {//it is work

        Users users = mapper.map(updateRequest, Users.class);


        users.setPassword(passwordEncoder.encode(updateRequest.getPassword()));

        userRepository.save(users);


    }

    @Override
    public void deleteUser(Long id) {//it is work
        if (userRepository.findById(id).isEmpty()) {
            throw new ArithmeticException("Do not find the user");
        } else {
            this.userRepository.deleteById(id);
            System.out.println("Delete is successful");
        }
    }

    @Override
    public TokenResponse login(Login login) {//it is work
        var user = userRepository.findByEmail(login.getEmail()).orElseThrow();
        var token = tokenManager.generateToken(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        log.info("Generate Token: " + login.getEmail());
        return TokenResponse.builder().token(token).username(login.getEmail()).name(user.getName()).surname(user.getLastName()).role(user.getRoles()).build();
    }
}
