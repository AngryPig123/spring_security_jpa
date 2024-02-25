package org.spring.example.jpa.util.exception;

import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;


@Getter
public class CustomAuthenticationException extends RuntimeException {

    private final AuthenticationException authenticationException;

    public CustomAuthenticationException(AuthenticationException authenticationException) {
        this.authenticationException = authenticationException;
    }

}
