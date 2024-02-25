package org.spring.example.jpa.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void main_greeting_test() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        get("/main")
                                .with(httpBasic("guest@gmail.com", "1q2w3e4r!"))
                )
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        Assertions.assertNotNull(modelAndView);

        String viewName = modelAndView.getViewName();
        Assertions.assertEquals("/main/index", viewName);

        Map<String, Object> model = modelAndView.getModel();
        Assertions.assertNotNull(model.get("greeting"));

    }

}
