package com.DaoVsDTO.security.controller; // <-- 1. Paquete corregido

import com.DaoVsDTO.security.dto.AuthenticationRequest;
import com.DaoVsDTO.security.dto.AuthenticationResponse;
import com.DaoVsDTO.security.dto.RegisterRequest;
import com.DaoVsDTO.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired; // Ya no es necesaria
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    // ✅ 2. Se elimina @Autowired. Lombok y Spring se encargan de la inyección.
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}