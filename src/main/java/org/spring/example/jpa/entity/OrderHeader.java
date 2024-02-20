package org.spring.example.jpa.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.example.jpa.dto.OrderHeaderDto;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "order_header")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderHeader {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", updatable = false)
    private Customer customer;

    @PrePersist
    private void prePersist() {
        orderDate = LocalDateTime.now();
    }   //  entity 가 영구 저장되기 전에 생성되어서 insert 된다.

    @Builder
    public OrderHeader(Customer customer) {
        this.customer = customer;
    }

    public OrderHeaderDto toDto() {
        return OrderHeaderDto.builder()
                .customer(customer)
                .orderDate(orderDate)
                .build();
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "customer=" + customer +
                '}';
    }

}
