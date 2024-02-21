package org.spring.example.jpa.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.example.jpa.entity.Auth;
import org.spring.example.jpa.entity.Role;
import org.spring.example.jpa.entity.RoleAuth;
import org.spring.example.jpa.entity.User;
import org.springframework.test.annotation.Commit;

@Commit
public class RepositoryTest extends RepositorySetup {

    @BeforeEach
    void beforeEach() {

        Role roleData = roleData();
        Role role = initRole(roleData);
        Assertions.assertNotNull(role);

        User userData = userData(role);
        User user = initUser(userData);
        Assertions.assertNotNull(user);

        Auth authData = authData();
        Auth auth = initAuth(authData);
        Assertions.assertNotNull(auth);

        RoleAuth roleAuthData = roleAuthData(role, auth);
        RoleAuth roleAuth = initRoleAuth(roleAuthData);
        Assertions.assertNotNull(roleAuth);

    }

    @Test
    void test_setup_valid() {
        //  @BeforeEach, @AfterEach, @BeforeAll, @AfterAll 작동을 검증한다.
    }

}
