package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "pizza")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pizza {

    @Id
    @Column(name = "pizza_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pizzaId;

    @Column(name = "name")
    private String name;

    @Column(name = "specification", nullable = false)
    private String specification;

    @Column(name = "price", nullable = false)
    private Long price;

    @OneToMany(mappedBy = "pizza")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Builder
    public Pizza(String name, String specification, Long price) {
        this.name = name;
        this.specification = specification;
        this.price = price;
    }
    
}
