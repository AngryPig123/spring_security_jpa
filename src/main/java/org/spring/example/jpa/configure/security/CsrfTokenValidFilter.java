package org.spring.example.jpa.configure.security;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class CsrfTokenValidFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Cookie[] cookies = ((HttpServletRequest) request).getCookies();

        Cookie _XSRF_TOKEN = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("XSRF-TOKEN".equals(cookie.getName())) {
                    _XSRF_TOKEN = cookie;
                }
            }
        }

        if (_XSRF_TOKEN != null) {
            log.info("XSRF-TOKEN = {}", _XSRF_TOKEN);
            log.info("XSRF-TOKEN.getName() = {}", _XSRF_TOKEN.getName());
            log.info("XSRF-TOKEN.getValue() = {}", _XSRF_TOKEN.getValue());
        }

        HttpSession session = httpServletRequest.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object attribute = session.getAttribute(name);
            log.info("attribute = {}", attribute);
        }

        filterChain.doFilter(request, response);
    }

}
