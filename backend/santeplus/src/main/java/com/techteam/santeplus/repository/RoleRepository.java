package com.techteam.santeplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techteam.santeplus.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(com.techteam.santeplus.model.ERole roleEnum);
}
