package org.spring.example.jpa.repository;

import org.spring.example.jpa.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
}
