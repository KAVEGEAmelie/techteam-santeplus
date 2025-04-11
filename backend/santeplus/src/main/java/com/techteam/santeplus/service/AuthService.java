package com.techteam.santeplus.service;

import com.techteam.santeplus.dto.RegisterRequest;
import com.techteam.santeplus.model.ERole;
import com.techteam.santeplus.model.Role;
import com.techteam.santeplus.model.User;
import com.techteam.santeplus.repository.RoleRepository;
import com.techteam.santeplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequest request) {
        // Vérifie si le nom d'utilisateur existe déjà
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Nom d'utilisateur déjà pris.";
        }

        // Vérifie si l'email est déjà utilisé
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email déjà utilisé.";
        }

        // Vérifie que le rôle est valide
        ERole roleEnum;
        try {
            roleEnum = ERole.valueOf("ROLE_" + request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Rôle invalide. Utilisez : PATIENT, MEDECIN ou ADMIN.";
        }

        // Récupère le rôle depuis la base de données
        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé dans la base de données."));

        // Crée l'utilisateur
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton(role)); // rôle unique pour le moment

        // Enregistre l'utilisateur
        userRepository.save(user);

        return "Inscription réussie !";
    }
}
