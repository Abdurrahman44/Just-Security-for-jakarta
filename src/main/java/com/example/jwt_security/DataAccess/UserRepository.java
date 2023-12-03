package com.example.jwt_security.DataAccess;

import com.example.jwt_security.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
   Optional<Users> findByEmail(String email );//Kullanıcıların olduğu Data yapılacak işlemlerin olduğu kısım


}
