package org.spring.example.jpa.security;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SecurityCsrfTest extends SecuritySetup {


    @Test
    @Disabled("noop")
    void csrf_invalid_token_fail() throws Exception {
        mockMvc.perform(options("/basic")
                        .with(csrf().asHeader().useInvalidToken())
                        .header("Origin", "http://localhost:8512")
                        .header("Access-Control-Request-Method", "GET")
                )
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Disabled("noop")
    void csrf_invalid_token_pass() throws Exception {
        mockMvc.perform(options("/basic")
                        .with(csrf().asHeader())
                        .header("Origin", "http://localhost:8512")
                        .header("Access-Control-Request-Method", "GET")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}
