package com.example.Odooraa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerUrl {
    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }
}
