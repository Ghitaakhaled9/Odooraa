package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.entities.UserSite;
import com.example.Odooraa.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "pagelogin"; // Assuming you have a login.html file in your templates directory
    }

    @PostMapping("/login/sucess")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {

        // Retrieve user by email from the database
        UserSite user = userRepository.findByEmail(email);
        System.out.println(user.getType());
        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // Check if the user is an admin
            if (user.getType() == UserType.ADMIN) {
                // Redirect to admin dashboard
                return "redirect:/dashbord";
            } else if (user.getType() == UserType.GERANT){

                return "redirect:/index";
            }
            else {
                return "redirect:/index";
            }
        } else {
            // Redirect back to login page with error message
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }
    }



}
