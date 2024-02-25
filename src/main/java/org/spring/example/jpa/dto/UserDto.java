package org.spring.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.example.jpa.entity.Role;
import org.spring.example.jpa.entity.User;
import org.spring.example.jpa.util.validator.annotation.IdDuplicateCheck;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @IdDuplicateCheck(tableName = "user", columnName = "email")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String address;

    private Role role;

    @Builder
    public UserDto(String email, String password, String address) {
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .address(address)
                .role(role)
                .build();
    }

}
