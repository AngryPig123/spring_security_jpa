package org.spring.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.spring.example.jpa.entity.Customer;
import org.spring.example.jpa.util.validator.annotation.IdDuplicateCheck;
import org.spring.example.jpa.util.validator.annotation.NotNullEmail;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @NotNullEmail
    @IdDuplicateCheck(tableName = "customer", columnName = "customer_email")
    private String customerEmail;

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
                .customerEmail(customerEmail)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phone(phone)
                .build();
    }

}
