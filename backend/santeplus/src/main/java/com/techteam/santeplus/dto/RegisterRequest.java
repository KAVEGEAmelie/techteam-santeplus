package com.techteam.santeplus.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String role; // PATIENT, MEDECIN, ADMIN
}