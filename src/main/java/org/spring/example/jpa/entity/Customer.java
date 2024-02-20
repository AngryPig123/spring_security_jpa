package org.spring.example.jpa.entity;


import jakarta.persistence.*;
import lombok.*;
import org.spring.example.jpa.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_email", nullable = false, updatable = false, unique = true)
    private String customerEmail;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Setter
    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<OrderHeader> orderHeaders = new ArrayList<>();

    @Builder
    public Customer(String customerEmail, String firstName, String lastName, String address, String phone) {
        this.customerEmail = customerEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public CustomerDto toDto() {
        return CustomerDto.builder()
                .customerEmail(customerEmail)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phone(phone)
                .build();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
