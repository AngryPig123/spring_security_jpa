package org.spring.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "role_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleAuth {

    @Id
    @Column(name = "role_auth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authRoleId;

    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "auth_id", updatable = false)
    private Auth auth;

    @Builder
    public RoleAuth(Role role, Auth auth) {
        this.role = role;
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "RoleAuth{" +
                "roleName=" + role.getName() +
                ", authName=" + auth.getName() +
                '}';
    }
}
