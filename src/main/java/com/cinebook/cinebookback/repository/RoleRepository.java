package com.cinebook.cinebookback.repository;

import com.cinebook.cinebookback.entity.Role;
import com.cinebook.cinebookback.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}