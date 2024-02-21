package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth", nullable = false)
    private LocalDate localDate;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false)
    private Role role;

    @Builder
    public User(String email, String password, String address, Role role) {
        this.email = email;
        this.password = password;
        this.localDate = LocalDate.now();
        this.address = address;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", localDate=" + localDate +
                ", address='" + address + '\'' +
                ", roleName=" + role.getName() +
                '}';
    }
}
