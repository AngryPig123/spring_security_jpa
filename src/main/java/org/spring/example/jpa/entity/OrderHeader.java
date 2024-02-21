package org.spring.example.jpa.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.spring.example.jpa.dto.OrderHeaderDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "order_header")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderHeader {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderHeader")    //  mappedBy 는 클래스명으로?
    private List<OrderLine> orderLines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "delivery_id", updatable = false)
    private Delivery delivery;

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

}
