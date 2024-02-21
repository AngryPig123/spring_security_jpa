package org.spring.example.jpa.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.entity.Auth;
import org.spring.example.jpa.entity.Role;
import org.spring.example.jpa.entity.RoleAuth;
import org.spring.example.jpa.entity.User;
import org.spring.example.jpa.repository.AuthRepository;
import org.spring.example.jpa.repository.AuthRoleRepository;
import org.spring.example.jpa.repository.RoleRepository;
import org.spring.example.jpa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final AuthRoleRepository authRoleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role roleData = roleData();
        Role role = initRole(roleData);


        User userData = userData(role);
        User user = initUser(userData);
        log.info("init user = {}", user);

        Auth authData = authData();
        Auth auth = initAuth(authData);


        RoleAuth roleAuthData = roleAuthData(role, auth);
        RoleAuth roleAuth = initRoleAuth(roleAuthData);
        log.info("init roleAuth = {}", roleAuth);
    }

    private User userData(Role role) {
        return User.builder()
                .email("anonymous@gmail.com")
                .password("1q2w3e4r!")
                .address("동작대로 xx길 xxx xx")
                .role(role)
                .build();
    }

    private Role roleData() {
        return Role.builder()
                .name("GUEST")
                .description("방문자")
                .build();
    }

    private Auth authData() {
        return Auth.builder()
                .name("main_enter")
                .description("메인 페이지 진입 권한")
                .url("/main")
                .build();
    }

    private RoleAuth roleAuthData(Role role, Auth auth) {
        return RoleAuth.builder()
                .role(role)
                .auth(auth)
                .build();
    }

    private User initUser(User user) {
        return userRepository.save(user);
    }

    private Role initRole(Role role) {
        return roleRepository.save(role);
    }

    private Auth initAuth(Auth auth) {
        return authRepository.save(auth);
    }

    private RoleAuth initRoleAuth(RoleAuth roleAuth) {
        return authRoleRepository.save(roleAuth);
    }

}
