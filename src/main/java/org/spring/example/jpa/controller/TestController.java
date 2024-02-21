package org.spring.example.jpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.service.CustomerService;
import org.spring.example.jpa.util.exception.CustomArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/test")
public class TestController {

    private final CustomerService customerService;

    @PostMapping("/customer-dto-validator")
    public ResponseEntity<String> customDtoValidator(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomArgumentNotValidException(bindingResult);
        } else {
            customerService.save(customerDto);
            log.info("customerDto = {}", customerDto);
            return ResponseEntity.ok("valid pass");
        }

    }

}
