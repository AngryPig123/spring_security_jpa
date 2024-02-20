package org.spring.example.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spring.example.jpa.dto.OrderHeaderDto;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.entity.OrderHeader;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class RepositoryTest extends RepositorySetup {

    @Test
    void initializer() {
    }

    @Test
    void customer_save_OK() {
        Customer saveCustomer = customer_save(customerDto.toEntity());
        Assertions.assertEquals(customerDto.getCustomerEmail(), saveCustomer.getCustomerEmail());
        Assertions.assertEquals(customerDto.getFirstName(), saveCustomer.getFirstName());
        Assertions.assertEquals(customerDto.getLastName(), saveCustomer.getLastName());
        Assertions.assertEquals(customerDto.getAddress(), saveCustomer.getAddress());
        Assertions.assertEquals(customerDto.getPhone(), saveCustomer.getPhone());
    }

    @Test
    @Commit
    void order_header_save_OK() {
        Customer saveCustomer = customer_save(customerDto.toEntity());
        OrderHeader saveOrderHeader = orderHeaderRepository.save(order_header_dto(saveCustomer).toEntity());
        Assertions.assertEquals(saveCustomer.getCustomerEmail(), saveOrderHeader.getCustomer().getCustomerEmail());
        Assertions.assertEquals(saveCustomer.getFirstName(), saveOrderHeader.getCustomer().getFirstName());
        Assertions.assertEquals(saveCustomer.getLastName(), saveOrderHeader.getCustomer().getLastName());
        Assertions.assertEquals(saveCustomer.getAddress(), saveOrderHeader.getCustomer().getAddress());
        Assertions.assertEquals(saveCustomer.getPhone(), saveOrderHeader.getCustomer().getPhone());
    }

    private Customer customer_save(Customer customer) {
        Customer save = customerRepository.save(customer);
        Assertions.assertNotNull(save);
        return save;
    }

    private OrderHeaderDto order_header_dto(Customer customer) {
        return OrderHeaderDto.builder()
                .customer(customer)
                .build();
    }

}
