package com.example.jwt_security.Business.concret;

import com.example.jwt_security.Business.Abstracts.RolService;
import com.example.jwt_security.DataAccess.RoleRepository;
import com.example.jwt_security.Entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void creatRequest(Role creatRequest) {
        roleRepository.save(creatRequest);
        log.info("create rols");
    }



    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
