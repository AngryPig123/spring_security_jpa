package org.spring.example.jpa.configure.security.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.entity.User;
import org.spring.example.jpa.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> usernameNotFoundExceptionSupplier = () -> new UsernameNotFoundException("problem during authentication!");
        User user = userRepository.findUserByEmail(email).orElseThrow(usernameNotFoundExceptionSupplier);
        log.info("JpaUserDetailsService user = {}", user);
        return new CustomUserDetails(user);
    }

}
