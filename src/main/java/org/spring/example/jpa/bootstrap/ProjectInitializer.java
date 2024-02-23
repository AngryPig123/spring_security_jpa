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
//@Component
@RequiredArgsConstructor
public class ProjectInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final AuthRoleRepository authRoleRepository;

    @Override
    public void run(String... args) throws Exception {

        //  firstUser email
        //  firstRole name, description
        //  firstAuth name, description, url

        Role firstRoleData = roleData();
        Role firstRole = initRole(firstRoleData);

        User firstUserData = userData(firstRole);
        User firstUser = initUser(firstUserData);
        log.info("init firstUser = {}", firstUser);

        Auth firstAuthData = authData();
        Auth firstAuth = initAuth(firstAuthData);

        RoleAuth firstRoleAuthData = roleAuthData(firstRole, firstAuth);
        RoleAuth firstRoleAuth = initRoleAuth(firstRoleAuthData);
        log.info("init firstRoleAuth = {}", firstRoleAuth);


        Role secondRoleData = roleData();
        secondRoleData.setName("ADMIN");
        secondRoleData.setDescription("관리자");
        Role secondRole = initRole(secondRoleData);

        User secondUserData = userData(secondRole);
        secondUserData.setEmail("admin@gmail.com");
        initUser(secondUserData);

        Auth secondAuthData = authData();
        Auth secondAuth = initAuth(secondAuthData);

        RoleAuth secondRoleAuthData = roleAuthData(secondRoleData, secondAuth);
        RoleAuth secondRoleAuth = initRoleAuth(secondRoleAuthData);
        log.info("init secondRoleAuth = {}", secondRoleAuth);


        Role anotherRoleData = roleData();
        anotherRoleData.setName("User");
        anotherRoleData.setDescription("사용자");
        Role anotherRole = initRole(anotherRoleData);

        User anotherUserData = userData(anotherRole);
        anotherUserData.setEmail("user@gmail.com");
        initUser(anotherUserData);

        Auth anotherAuthData = authData();
        Auth anotherAuth = initAuth(anotherAuthData);

        RoleAuth anotherRoleAuthData = roleAuthData(anotherRoleData, anotherAuth);
        RoleAuth anotherRoleAuth = initRoleAuth(anotherRoleAuthData);
        log.info("init anotherRoleAuth = {}", anotherRoleAuth);


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
