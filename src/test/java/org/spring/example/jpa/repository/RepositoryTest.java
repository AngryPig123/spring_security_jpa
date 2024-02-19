package org.spring.example.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.service.CustomerServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class RepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customerDto =
                CustomerDto.builder()
                        .customerId("johnDoe@gmail.com")
                        .firstName("john")
                        .lastName("doe")
                        .address("동작대로 xx길 xxx xx")
                        .phone("555-0101")
                        .build();
    }

    @Test
    void customer_save_ok_1() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customerDto.toEntity());
        CustomerDto save = customerService.save(new CustomerDto());
        Assertions.assertNotNull(save);
        Assertions.assertNotEquals(customerDto.getPhone(), save.getPhone());
        Assertions.assertEquals(save.getPhone(),"mock test!!!");
    }

}
