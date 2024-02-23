package org.spring.example.jpa.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @GetMapping
    public String adminGet() {
        return "adminGet";
    }

    @PostMapping
    public String adminPost() {
        return "adminPost";
    }

    @PutMapping
    public String adminPut() {
        return "adminPut";
    }

    @PatchMapping
    public String adminPatch() {
        return "adminPatch";
    }

    @DeleteMapping
    public String adminDelete() {
        return "adminDelete";
    }

}
