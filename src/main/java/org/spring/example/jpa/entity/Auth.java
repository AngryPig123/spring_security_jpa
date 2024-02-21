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
@Table(name = "auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {

    @Id
    @Column(name = "auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

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
