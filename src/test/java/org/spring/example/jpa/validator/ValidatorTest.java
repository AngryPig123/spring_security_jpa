package org.spring.example.jpa.validator;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;
import java.util.Set;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private MessageSource messageSource;

    private CustomerDto customerDto;

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
    }

    @Test
    void id_duplicated_validator() {

        Set<ConstraintViolation<CustomerDto>> validated = validator.validate(customerDto);
        Assertions.assertEquals(0, validated.size());

        customerService.save(customerDto);

        validated = validator.validate(customerDto);
        Assertions.assertEquals(1, validated.size());

        ConstraintViolation<CustomerDto> violation = validated.iterator().next();
        String errorMessage = violation.getMessage();
        Assertions.assertEquals(messageSource.getMessage("validation.duplicated.id", null, Locale.KOREA), errorMessage);

    }

}
