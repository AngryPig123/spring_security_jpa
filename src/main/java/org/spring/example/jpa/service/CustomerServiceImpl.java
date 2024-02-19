package org.spring.example.jpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer save = customerRepository.save(customerDto.toEntity());
        log.info("save entity = {}", save);
        save.setPhone("mock test!!!");
        return save.toDto();
    }

}
