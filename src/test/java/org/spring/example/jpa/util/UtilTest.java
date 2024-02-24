package org.spring.example.jpa.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class UtilTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    void bcrypt_password_encoder_test() {
        String encode = bCryptPasswordEncoder.encode("1q2w3e4r!");
        log.info("encode = {}", encode);
    }

}
