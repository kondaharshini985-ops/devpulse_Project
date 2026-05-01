package com.example.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityconfig.jwtUtil;
import com.example.user.entity.User;
import com.example.user.repository.UserRepo;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final jwtUtil jwtutil;
    private final UserRepo userRepo;

    public AuthController(AuthenticationManager authManager,
                          jwtUtil jwtUtil,
                          UserRepo userRepo) {
        this.authManager = authManager;
        this.jwtutil = jwtUtil;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto) {

        try {
            // Step 1: validate email and password
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmail(),
                            dto.getPassword()
                    )
            );

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password");
        }

        // Step 2: fetch user from DB
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 3: generate token
        String token = jwtutil.generateToken(dto.getEmail());

        // Step 4: return response
        return ResponseEntity.ok(new LoginResponseDTO(
               
                user.getUsername(),
                user.getRole(),
                token,
                user.getEmail()
                
        ));
    }
}