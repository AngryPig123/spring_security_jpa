package org.spring.example.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.dto.OrderHeaderDto;
import org.spring.example.jpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class RepositorySetup {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected OrderHeaderRepository orderHeaderRepository;

    protected CustomerDto customerDto;

    protected OrderHeaderDto orderHeaderDto;

    @BeforeEach
    void beforeEach() {
        customerDto =
                CustomerDto.builder()
                        .customerId("johnDoe@gmail.com")
                        .firstName("john")
                        .lastName("doe")
                        .address("동작대로 xx길 xxx xx")
                        .phone("555-0101")
                        .build();
        Customer customerEntity = customerDto.toEntity();
        orderHeaderDto =
                OrderHeaderDto.builder()
                        .customer(customerEntity)
                        .build();
    }

    @AfterEach
    void afterEach() {
        customerDto =
                CustomerDto.builder()
                        .customerId("johnDoe@gmail.com")
                        .firstName("john")
                        .lastName("doe")
                        .address("동작대로 xx길 xxx xx")
                        .phone("555-0101")
                        .build();
        Customer customerEntity = customerDto.toEntity();
        orderHeaderDto =
                OrderHeaderDto.builder()
                        .customer(customerEntity)
                        .build();
    }

}
