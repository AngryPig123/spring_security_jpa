package org.spring.example.jpa.util.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class CustomArgumentNotValidException extends RuntimeException {

    private final BindingResult bindingResult;

    public CustomArgumentNotValidException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

}
