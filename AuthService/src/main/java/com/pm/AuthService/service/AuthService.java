package com.pm.AuthService.service;

import com.pm.AuthService.dto.LoginRequestDTO;
import com.pm.AuthService.model.User;
import com.pm.AuthService.util.JWTUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil =jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO){
        Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(),
                        u.getPassword())).
                map(u -> jwtUtil.generateToken(u.getEmail(),u.getRole()));
        return token;
    }

}
