package org.spring.example.jpa.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/guest")
public class GuestController {

    @GetMapping
    public String guestGet() {
        return "guestGet";
    }

    @PostMapping
    public String guestPost() {
        return "guestPost";
    }

    @PutMapping
    public String guestPut() {
        return "guestPut";
    }

    @PatchMapping
    public String guestPatch() {
        return "guestPatch";
    }

    @DeleteMapping
    public String guestDelete() {
        return "guestDelete";
    }

}
