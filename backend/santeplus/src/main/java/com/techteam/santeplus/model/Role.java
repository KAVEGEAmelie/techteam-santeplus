package com.techteam.santeplus.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles") // Optionnel mais recommandé
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name; // Enum: ADMIN, MEDECIN, PATIENT
}
