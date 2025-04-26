package com.example.simpleweb.controller;

import com.example.myutils.StringHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home(Model model) {
        String msg = StringHelper.shout("hello from myutils");
        model.addAttribute("message", msg);
        return "index";
    }
}
