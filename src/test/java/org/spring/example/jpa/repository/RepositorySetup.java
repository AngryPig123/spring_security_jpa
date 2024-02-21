package org.spring.example.jpa.repository;

import org.spring.example.jpa.entity.Auth;
import org.spring.example.jpa.entity.Role;
import org.spring.example.jpa.entity.RoleAuth;
import org.spring.example.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class RepositorySetup {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AuthRepository authRepository;

    @Autowired
    protected AuthRoleRepository authRoleRepository;

    protected User userData(Role role) {
        return User.builder()
                .email("anonymous@gmail.com")
                .password("1q2w3e4r!")
                .address("동작대로 xx길 xxx xx")
                .role(role)
                .build();
    }

    protected Role roleData() {
        return Role.builder()
                .name("GUEST")
                .description("방문자")
                .build();
    }

    protected Auth authData() {
        return Auth.builder()
                .name("main_enter")
                .description("메인 페이지 진입 권한")
                .url("/main")
                .build();
    }

    protected RoleAuth roleAuthData(Role role, Auth auth) {
        return RoleAuth.builder()
                .role(role)
                .auth(auth)
                .build();
    }

    protected User initUser(User user) {
        return userRepository.save(user);
    }

    protected Role initRole(Role role) {
        return roleRepository.save(role);
    }

    protected Auth initAuth(Auth auth) {
        return authRepository.save(auth);
    }

    protected RoleAuth initRoleAuth(RoleAuth roleAuth) {
        return authRoleRepository.save(roleAuth);
    }
    
}
