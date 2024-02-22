package org.spring.example.jpa.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/basic")
public class BasicController {

    @GetMapping
    public String basicGet() {
        return "basicGet";
    }

    @PostMapping
    public String basicPost() {
        return "basicPost";
    }

    @PutMapping
    public String basicPut() {
        return "basicPut";
    }

    @PatchMapping
    public String basicPatch() {
        return "basicPatch";
    }

    @DeleteMapping
    public String basicDelete() {
        return "basicDelete";
    }

}
