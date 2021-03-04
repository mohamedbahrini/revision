package com.example.revision.dao;

import com.example.revision.model.Role;
import com.example.revision.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleUser);
}
