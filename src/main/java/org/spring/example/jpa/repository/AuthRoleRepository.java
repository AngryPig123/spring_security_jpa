package org.spring.example.jpa.repository;

import org.spring.example.jpa.entity.RoleAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<RoleAuth, Long> {
}
