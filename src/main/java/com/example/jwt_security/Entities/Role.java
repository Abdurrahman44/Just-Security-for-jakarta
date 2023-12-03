package com.example.jwt_security.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

//    @ManyToMany
//            @JoinTable(
//                    name = "role_user",
//                    joinColumns = @JoinColumn(name = "role_id"),
//                    inverseJoinColumns = @JoinColumn(name = "user_id")
//
//
//            )
//
//    Set<Users> JoinUser=new HashSet<>();


}
