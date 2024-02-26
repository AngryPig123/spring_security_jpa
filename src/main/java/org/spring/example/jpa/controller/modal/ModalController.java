package org.spring.example.jpa.controller.modal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping(path = "/modal")
public class ModalController {

    @GetMapping
    public String modalController(
            @RequestParam(value = "param", required = false) String param, Model model
    ) {
        return "/modal/authentication";
    }

}
