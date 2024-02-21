package org.spring.example.jpa.repository;

import org.spring.example.jpa.entity.Role;
import org.spring.example.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
