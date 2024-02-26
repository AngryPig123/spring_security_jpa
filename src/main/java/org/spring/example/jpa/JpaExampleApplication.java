package org.spring.example.jpa;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JpaExampleApplication {

    @Value("${server.port}")
    private String SERVER_PORT;

    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApplication.class, args);
    }

    @PostConstruct
    void init() {
        serverPortPrint();
    }

    private void serverPortPrint() {
        log.info(String.format("http://localhost:%s", SERVER_PORT));
    }

}
