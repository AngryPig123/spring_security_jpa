package org.spring.example.jpa.controller;

import jakarta.validation.Valid;
import lombok.*;
import org.spring.example.jpa.dto.CustomerDto;
import org.spring.example.jpa.service.CustomerService;
import org.spring.example.jpa.validator.IdDuplicateCheck;
import org.spring.example.jpa.validator.NotNullEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final CustomerService customerService;

    @PostMapping("/validator")
    public ResponseEntity<String> validator(@Valid @RequestBody TestReq testReq) {
        return ResponseEntity.ok("valid ok");
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok("valid ok");
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestReq {

        @NotNullEmail(message = "is not null!!")
        private String email;

        @IdDuplicateCheck(tableName = "customer", columnName = "customer_id")
        private String id;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
