package org.spring.example.jpa.util.request;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import java.util.List;

@Getter
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class RequestInformation {
    private String requestUrl;
    private HttpMethod httpMethod;
    private List<Cookie> cookies;


    public void printInfo() {
        log.info("requestUrl = {}", requestUrl);
        log.info("httpMethod = {}", httpMethod);
        for (Cookie cookie : this.cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            log.info("cookie name = {}", name);
            log.info("cookie value = {}", value);
        }
    }
    
}
