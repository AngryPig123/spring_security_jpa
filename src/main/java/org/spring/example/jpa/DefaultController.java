package org.spring.example.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class DefaultController {

    @GetMapping
    public String directController() {
        return "redirect:/login-form";
    }

}
