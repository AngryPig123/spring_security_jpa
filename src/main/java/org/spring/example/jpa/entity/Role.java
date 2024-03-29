package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {


    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Setter
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Setter
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "role")
    private List<RoleAuth> roleAuths = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    @Builder
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
