package com.techteam.santeplus.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techteam.santeplus.dto.LoginRequest;
import com.techteam.santeplus.dto.RegisterRequest;
import com.techteam.santeplus.service.AuthService;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String result = authService.registerUser(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // À implémenter avec JWT ou session
        return ResponseEntity.ok("Connexion simulée pour " + request.getUsername());
    }
}
