package org.spring.example.jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.configure.security.user.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(path = "/login-form")
public class LoginFormController {

    @GetMapping
    public String loginForm() {

//        CustomUserDetails customUserDetails = null;
//
//        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (principal instanceof CustomUserDetails) {
//                customUserDetails = (CustomUserDetails) principal;
//            }
//        }
//        log.info("customUserDetails = {}", customUserDetails.getUser());

        return "/login/form";
    }

    @GetMapping("/fail")
    public String loginFailForm() {
        return "/login/fail-form";
    }

}
