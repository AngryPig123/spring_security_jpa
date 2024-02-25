package org.spring.example.jpa.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(path = "/main")
public class MainController {

    @GetMapping
    public String defaultMain(Authentication authentication, Model model) {
        authenticationPrintHelper(authentication);
        String name = authentication.getName();
        String greeting = String.format("%s님 반갑습니다", name);
        model.addAttribute("greeting", greeting);
        return "/main/form";
    }

    private void authenticationPrintHelper(Authentication authentication) {
        String name = authentication.getName();
        log.info("name = {}", name);
    }

}
