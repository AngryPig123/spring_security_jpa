package org.spring.example.jpa.security;

import org.junit.jupiter.api.BeforeEach;
import org.spring.example.jpa.configure.security.CustomCorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
public abstract class SecuritySetup {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private CustomCorsConfig customCorsConfig;

//    protected CorsConfiguration corsConfiguration;
//    protected MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

//        corsConfiguration = customCorsConfig.getCorsConfiguration(httpServletRequest);
    }




}
