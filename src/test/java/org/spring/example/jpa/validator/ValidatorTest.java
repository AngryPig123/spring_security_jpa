package org.spring.example.jpa.validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.repository.CustomerRepository;
import org.spring.example.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;
import java.util.Set;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LocalValidatorFactoryBean validator;

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

    @BeforeEach
    void afterEach() {
        customerDto =
                CustomerDto.builder()
                        .customerId("johnDoe@gmail.com")
                        .firstName("john")
                        .lastName("doe")
                        .address("동작대로 xx길 xxx xx")
                        .phone("555-0101")
                        .build();

        customerRepository.deleteCustomerByCustomerId(customerDto.getCustomerId());

    }

    @Test
    @Order(1)
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

    @Test
    @Order(2)
    void validator_integrated_test_valid_pass() throws Exception {
        ResultActions resultActions = customerDtoValidatorRequest(customerDto);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("valid pass", responseBody);
    }

    @Test
    @Order(3)
    void validator_integrated_test_valid_fail_case1_emptyId() throws Exception {
        String notNullEmail = messageSource.getMessage("validation.not.null.email", null, Locale.getDefault());
        customerDto.setCustomerId("");
        ResultActions resultActions = customerDtoValidatorRequest(customerDto);
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Is.is(notNullEmail)));
    }

    @Test
    @Order(4)
    void validator_integrated_test_valid_fail_case2_duplicateId() throws Exception {
        CustomerDto save = customerService.save(customerDto);
        Assertions.assertNotNull(save);
        String duplicateId = messageSource.getMessage("validation.duplicated.id", null, Locale.getDefault());
        ResultActions resultActions = customerDtoValidatorRequest(customerDto);
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.customerId", Is.is(duplicateId)));
    }

    private ResultActions customerDtoValidatorRequest(CustomerDto customerDto) throws Exception {
        String req = objectMapper.writeValueAsString(customerDto);
        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/test/customer-dto-validator")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(MockMvcResultHandlers.print());
    }

}
