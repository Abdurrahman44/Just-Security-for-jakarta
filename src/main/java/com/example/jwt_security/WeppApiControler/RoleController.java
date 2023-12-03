package com.example.jwt_security.WeppApiControler;

import com.example.jwt_security.Business.Abstracts.RolService;
import com.example.jwt_security.Entities.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class RoleController {
    private final RolService usersService;

    public RoleController(RolService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/roless")
    public String test() {
        return "Tests";
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<?> create(@RequestBody Role dto){
        usersService.creatRequest(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
