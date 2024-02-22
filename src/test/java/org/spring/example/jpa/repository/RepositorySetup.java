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

}
