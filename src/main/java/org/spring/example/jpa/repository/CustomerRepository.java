package org.spring.example.jpa.repository;

import org.spring.example.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    void deleteCustomerByCustomerId(String customerId);
}
