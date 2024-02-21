package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "order_line")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {


    @Id
    @Column(name = "order_line_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;

    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false)
    private OrderHeader orderHeader;

    @ManyToOne
    @JoinColumn(name = "pizza_id", updatable = false)
    private Pizza pizza;

    @Column(name = "quantity")
    private Short quantity;

    @Builder
    public OrderLine(OrderHeader orderHeader, Pizza pizza, Short quantity) {
        this.orderHeader = orderHeader;
        this.pizza = pizza;
        this.quantity = quantity;
    }

}
