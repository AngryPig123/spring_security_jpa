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

    //  @NotNull : 제한된 필드에 null 허용 하지 않음, 비어 있을 수 있음.
    //  @NotEmpty : 제한된 필드에 null 허용 하지 않음, 길이가 0보다 커야함. @Size 와 함께 사용.
    //  @NotBlank : 제한된 필드에 null 허용 하지 않음, 길이는 0보다 커야함.

    @NotEmpty
    @NotNullEmail  //  null 허용.
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
