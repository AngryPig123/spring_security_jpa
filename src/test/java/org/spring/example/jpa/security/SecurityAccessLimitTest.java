package org.spring.example.jpa.security;

import org.junit.jupiter.api.*;
import org.springframework.http.HttpMethod;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecurityAccessLimitTest extends SecuritySetup {

    @Nested
    @Order(1)
    @DisplayName("admin test")
    class AdminTest {
        @Test
        @Order(1)
        void admin_get_pass() throws Exception {
            accessLimitTestHelper("/admin", admin, HttpMethod.GET).andExpect(status().isOk());
        }

        @Test
        @Order(2)
        void user_get_fail() throws Exception {
            accessLimitTestHelper("/user", admin, HttpMethod.POST).andExpect(status().isForbidden());
        }

        @Test
        @Order(3)
        void guest_get_fail() throws Exception {
            accessLimitTestHelper("/guest", admin, HttpMethod.PATCH).andExpect(status().isForbidden());
        }

        @Test
        @Order(4)
        void basic_get_pass() throws Exception {
            accessLimitTestHelper("/basic", admin, HttpMethod.PUT).andExpect(status().isOk());
        }
    }

    @Nested
    @Order(2)
    @DisplayName("user test")
    class UserTest {
        @Test
        @Order(1)
        void admin_get_fail() throws Exception {
            accessLimitTestHelper("/admin", user, HttpMethod.GET).andExpect(status().isForbidden());
        }

        @Test
        @Order(2)
        void user_get_pass() throws Exception {
            accessLimitTestHelper("/user", user, HttpMethod.POST).andExpect(status().isOk());
        }

        @Test
        @Order(3)
        void guest_get_fail() throws Exception {
            accessLimitTestHelper("/guest", user, HttpMethod.PATCH).andExpect(status().isForbidden());
        }

        @Test
        @Order(4)
        void basic_get_fail() throws Exception {
            accessLimitTestHelper("/basic", user, HttpMethod.PUT).andExpect(status().isOk());
        }
    }

    @Nested
    @Order(3)
    @DisplayName("guest test")
    class GuestTest {
        @Test
        @Order(1)
        void admin_get_fail() throws Exception {
            accessLimitTestHelper("/admin", guest, HttpMethod.GET).andExpect(status().isForbidden());
        }

        @Test
        @Order(2)
        void user_get_fail() throws Exception {
            accessLimitTestHelper("/user", guest, HttpMethod.POST).andExpect(status().isForbidden());
        }

        @Test
        @Order(3)
        void guest_get_pass() throws Exception {
            accessLimitTestHelper("/guest", guest, HttpMethod.PATCH).andExpect(status().isOk());
        }

        @Test
        @Order(4)
        void basic_get_pass() throws Exception {
            accessLimitTestHelper("/basic", guest, HttpMethod.PUT).andExpect(status().isOk());
        }
    }

}
