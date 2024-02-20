package org.spring.example.jpa.dto;

import jakarta.persistence.*;
import lombok.*;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.entity.OrderHeader;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderHeaderDto {

    private Customer customer;
    private LocalDateTime orderDate;
    
    @Builder
    public OrderHeaderDto(Customer customer, LocalDateTime orderDate) {
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public OrderHeader toEntity() {
        return OrderHeader.builder()
                .customer(customer)
                .build();
    }

}
