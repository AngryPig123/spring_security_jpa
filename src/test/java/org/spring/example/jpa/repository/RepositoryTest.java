package org.spring.example.jpa.repository;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spring.example.jpa.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Commit
@Slf4j
public class RepositoryTest extends RepositorySetup {

    @Test
    void custom_user_details_get_authorities_test() {
        Optional<User> user = userRepository.findUserByEmail("anonymous@gmail.com");
        log.info("user = {}", user);
        Assertions.assertNotEquals(Optional.empty(), user);
        List<SimpleGrantedAuthority> collect = user.get().getRole().getRoleAuths().stream()
                .map(a -> new SimpleGrantedAuthority(
                        a.getAuth().getName()
                )).collect(Collectors.toList());
        Assertions.assertEquals(1, collect.size());
    }

}
