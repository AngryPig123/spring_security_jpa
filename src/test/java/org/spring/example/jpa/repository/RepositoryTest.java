package org.spring.example.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.entity.OrderHeader;

@Slf4j
public class RepositoryTest extends RepositorySetup {

    @Test
    void initializer() {
    }

    @Test
    void customer_save_OK() {
        Customer save = customerRepository.save(customerDto.toEntity());
        Assertions.assertNotNull(save);
        Assertions.assertEquals(customerDto.getCustomerId(), save.getCustomerId());
        Assertions.assertEquals(customerDto.getFirstName(), save.getFirstName());
        Assertions.assertEquals(customerDto.getLastName(), save.getLastName());
        Assertions.assertEquals(customerDto.getAddress(), save.getAddress());
        Assertions.assertEquals(customerDto.getPhone(), save.getPhone());
    }

    @Test
    void order_header_save_OK() {
        OrderHeader save = orderHeaderRepository.save(orderHeaderDto.toEntity());
        Assertions.assertNotNull(save);
        Assertions.assertEquals(orderHeaderDto.getCustomer(), save.getCustomer());
    }

}
