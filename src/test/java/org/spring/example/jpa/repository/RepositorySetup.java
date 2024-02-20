package org.spring.example.jpa.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.spring.example.jpa.dto.CustomerDto;
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

    @BeforeEach
    void beforeEach() {
        customerDto =
                CustomerDto.builder()
                        .customerEmail("johnDoe@gmail.com")
                        .firstName("john")
                        .lastName("doe")
                        .address("동작대로 xx길 xxx xx")
                        .phone("555-0101")
                        .build();
    }

    @AfterEach
    void afterEach() {
        customerDto =
                CustomerDto.builder()
                        .customerEmail("johnDoe@gmail.com")
                        .firstName("john")
                        .lastName("doe")
                        .address("동작대로 xx길 xxx xx")
                        .phone("555-0101")
                        .build();
    }

}
