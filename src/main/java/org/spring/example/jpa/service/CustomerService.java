package org.spring.example.jpa.service;

import org.spring.example.jpa.dto.CustomerDto;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);
}
