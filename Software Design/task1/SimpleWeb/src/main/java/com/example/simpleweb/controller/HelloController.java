package com.example.simpleweb.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home(Model model) {
        String msg = "Spring Boot is Awesome!";
        System.out.println("🔔 Backend hit! Logging from HelloController...");
        System.out.println("Using commons-lang3 to reverse message: " + StringUtils.reverse(msg));
        model.addAttribute("message", msg);
        return "index";
    }
}
