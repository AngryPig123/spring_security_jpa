package org.spring.example.jpa.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @GetMapping
    public String userGet() {
        return "userGet";
    }

    @PostMapping
    public String userPost() {
        return "userPost";
    }

    @PutMapping
    public String userPut() {
        return "userPut";
    }

    @PatchMapping
    public String userPatch() {
        return "userPatch";
    }

    @DeleteMapping
    public String userDelete() {
        return "userDelete";
    }

}
