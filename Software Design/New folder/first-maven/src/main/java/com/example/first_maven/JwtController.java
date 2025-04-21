package com.example.first_maven;

import com.example.jwt.JwtGenerator;
import com.example.vowel.VowelCounter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JwtController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/process")
    public String processName(@RequestParam String name, Model model) {
        // Generate JWT
        String jwt = JwtGenerator.generateJwt(name);

        // Count vowels
        int vowelCount = VowelCounter.countVowels(name);

        // Add to model
        model.addAttribute("name", name);
        model.addAttribute("jwt", jwt);
        model.addAttribute("vowelCount", vowelCount);

        // Log to console
        System.out.println("Name: " + name);
        System.out.println("JWT: " + jwt);
        System.out.println("Vowel count: " + vowelCount);

        return "result";
    }
}
