package com.project.todomanagementbackend.repository;

import com.project.todomanagementbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
