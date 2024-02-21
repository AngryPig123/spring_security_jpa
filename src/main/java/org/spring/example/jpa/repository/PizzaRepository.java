package org.spring.example.jpa.repository;

import org.spring.example.jpa.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
