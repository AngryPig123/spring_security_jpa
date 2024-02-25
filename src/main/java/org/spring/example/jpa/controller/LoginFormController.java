package org.spring.example.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/login-form")
public class LoginFormController {

    @GetMapping
    public String loginForm() {
        return "/login/form";
    }

    @GetMapping("/fail")
    public String loginFailForm() {
        return "/login/fail-form";
    }

}
