package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {

    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "url", nullable = false)
    private String url;

    @OneToMany(mappedBy = "auth")
    private List<RoleAuth> roleAuths = new ArrayList<>();

    @Builder
    public Auth(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

}
