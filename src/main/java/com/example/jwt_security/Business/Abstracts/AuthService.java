
package com.example.jwt_security.Business.Abstracts;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthServiceImpl authService;
}