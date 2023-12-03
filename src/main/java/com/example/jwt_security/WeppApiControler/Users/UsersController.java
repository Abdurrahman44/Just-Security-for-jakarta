package com.example.jwt_security.WeppApiControler.Users;

import com.example.jwt_security.Business.Abstracts.UsersService;
import com.example.jwt_security.DTO.Request.User.CreateRequest;
import com.example.jwt_security.DTO.Request.User.Login;
import com.example.jwt_security.DTO.Request.User.TokenResponse;
import com.example.jwt_security.DTO.Request.User.UpdateRequest;
import com.example.jwt_security.DTO.Response.User.GetAllUsersResponse;
import com.example.jwt_security.DTO.Response.User.GetByIdUsersResponse;
import com.example.jwt_security.DataAccess.UserRepository;
import com.example.jwt_security.Entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController//annatation
@RequestMapping("/api/users")
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class UsersController {
    private final UsersService usersService;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    public UsersController(UsersService usersService, UserRepository userRepository,ModelMapper mapper) {
        this.usersService = usersService;
        this.userRepository = userRepository;
        this.mapper=mapper;
    }

    @PostMapping(value = "/login")
    public TokenResponse login(@RequestBody Login loginRequest) {
        return usersService.login(loginRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public GetByIdUsersResponse getId(@PathVariable Long id) {
        return usersService.getId(id);
    }


    @GetMapping(value = "/alluser")
    public List<GetAllUsersResponse> getAll() {
        List<Users> Users = userRepository.findAll();

        return Users.stream().map(user -> mapper.map(user, GetAllUsersResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void creatUser(@RequestBody CreateRequest createRequest) {
        this.usersService.creatRequest(createRequest);
    }

    @PutMapping(value = "/update")
    public void updateUser(@RequestBody UpdateRequest updateRequest) {

        this.usersService.updateRequest(updateRequest);
    }

   // @Secured("ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete/{id}") //it is work
    public void deleteUser(@PathVariable Long id) {
        this.usersService.deleteUser(id);
    }


}
