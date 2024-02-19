package org.spring.example.jpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.validator.NotNullEmail;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @NotEmpty
    @NotNullEmail
    private String customerId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    public Customer toEntity() {
        return Customer.builder()
                .customerId(customerId)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phone(phone)
                .build();
    }

}
