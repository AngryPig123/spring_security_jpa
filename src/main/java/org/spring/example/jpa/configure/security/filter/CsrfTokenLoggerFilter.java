package org.spring.example.jpa.configure.security.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CsrfTokenLoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Object o = request.getAttribute("_csrf");
        CsrfToken csrfToken = (CsrfToken) o;

        log.info("csrfToken.getParameterName = {}", csrfToken.getParameterName());
        log.info("csrfToken.getHeaderName = {}", csrfToken.getHeaderName());
        log.info("csrfToken.getToken = {}", csrfToken.getToken());

        filterChain.doFilter(request, response);
    }

}
