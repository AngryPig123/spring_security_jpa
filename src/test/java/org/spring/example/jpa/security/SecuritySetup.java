package org.spring.example.jpa.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
public abstract class SecuritySetup {
    protected MockMvc mockMvc;

    protected AccessUser admin;

    protected AccessUser user;

    protected AccessUser guest;

    @BeforeEach
    public void setup(WebApplicationContext wac) {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        admin = AccessUser.builder()
                .email("admin@gmail.com")
                .password("1q2w3e4r!")
                .build();

        user = AccessUser.builder()
                .email("user@gmail.com")
                .password("1q2w3e4r!")
                .build();

        guest = AccessUser.builder()
                .email("guest@gmail.com")
                .password("1q2w3e4r!")
                .build();

    }


    protected ResultActions accessLimitTestHelper(String endPoint, AccessUser accessUser, HttpMethod httpMethod) throws Exception {

        Assertions.assertNotNull(endPoint);
        Assertions.assertNotNull(accessUser);
        Assertions.assertNotNull(httpMethod);

        MockHttpServletRequestBuilder requestBuilder = switch (httpMethod.name()) {
            case "GET" -> get(endPoint);
            case "POST" -> post(endPoint);
            case "PATCH" -> patch(endPoint);
            case "PUT" -> put(endPoint);
            case "DELETE" -> delete(endPoint);
            default -> throw new IllegalArgumentException("지원되지 않는 HTTP 메소드입니다.");
        };

        // HTTP 기본 인증 및 헤더 추가
        requestBuilder
                .with(httpBasic(accessUser.getEmail(), accessUser.getPassword()));

        return mockMvc.perform(requestBuilder);
    }



    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class AccessUser {
        private String email;
        private String password;
    }

}
