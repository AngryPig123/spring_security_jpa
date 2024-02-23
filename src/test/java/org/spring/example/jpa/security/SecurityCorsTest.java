package org.spring.example.jpa.security;

import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SecurityCorsTest extends SecuritySetup {

    //  mvc.perform(post("/").with(csrf()))
    //  mvc.perform(post("/").with(csrf().asHeader()))
    //  mvc.perform(post("/").with(csrf().useInvalidToken()))

    @Test
    void get_basic_get_pass() throws Exception {
        mockMvc.perform(options("/basic")
                        .with(csrf().useInvalidToken())
                        .header("Origin", "http://localhost:8512")
                        .header("Access-Control-Request-Method", "GET")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void get_basic_post_pass() throws Exception {
        mockMvc.perform(options("/basic")
                        .header("Origin", "http://localhost:8512")
                        .header("Access-Control-Request-Method", "POST")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void get_basic_get_fail() throws Exception {
        mockMvc.perform(options("/basic")
                        .header("Origin", "https://www.google.com")
                        .header("Access-Control-Request-Method", "GET")
                )
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void get_basic_post_fail() throws Exception {
        mockMvc.perform(options("/basic")
                        .header("Origin", "https://www.google.com")
                        .header("Access-Control-Request-Method", "POST")
                )
                .andExpect(status().isForbidden())
                .andDo(print());
    }

}
