package com.example.Odooraa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class singlePage {

    @GetMapping("/single")
    public String showSinglePage() {
        return "single"; // Return the view name for the single product page
    }
}
