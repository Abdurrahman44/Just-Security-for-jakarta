package com.example.jwt_security.Business.Abstracts;

import com.example.jwt_security.Entities.Role;

import java.util.List;

public interface RolService {
   void creatRequest(Role creatRequest);
   List<Role> getAll();


}
