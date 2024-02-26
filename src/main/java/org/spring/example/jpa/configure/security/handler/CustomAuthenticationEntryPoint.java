package org.spring.example.jpa.configure.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.util.request.HttpServletRequestService;
import org.spring.example.jpa.util.request.RequestInformation;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    private final HttpServletRequestService httpServletRequestService;

    //  401 handler
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("authentication entrypoint = {}", authException.getMessage());
        RequestInformation requestInformation = httpServletRequestService.requestInfo(request);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.sendRedirect("/login-form?error=authentication");
    }

}
