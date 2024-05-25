package com.example.Odooraa.Controller;

import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.Service.ProduitService;
import com.example.Odooraa.entities.Produit;
import com.example.Odooraa.entities.UserSite;
import com.example.Odooraa.entities.UserType;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    private final ProduitService produitService;

    @Autowired
    public LoginController(ProduitService produitService) {
        this.produitService = produitService;
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "pagelogin"; // Assuming you have a login.html file in your templates directory
    }

    @PostMapping("/login/sucess")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        // Retrieve user by email from the database
        UserSite user = userRepository.findByEmail(email);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            // Store user information in session
            session.setAttribute("user", user);

            // Check user type and redirect accordingly
            if (user.getType() == UserType.ADMIN) {
                // Redirect to admin dashboard
                return "redirect:/dashbord";
            } else if (user.getType() == UserType.GERANT) {
                return "redirect:/dashboardUser";
            } else if(user.getType() == UserType.CLIENT){
                return "redirect:/indexAfterInscription";
            }else{
                return "redirect:/error";
            }
        } else {
            // Redirect back to login page with error message
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }
    }
    @GetMapping("/indexAfterInscription")
    public String showIndexAfterInscriptionPage(Model model) {
        List<Produit> allProducts = produitService.getAllProduits();
        model.addAttribute("allProducts", allProducts);

        // Retrieve top best-selling products
        List<Produit> bestSellingProducts = produitService.getTopBestSellingProducts(10); // Change 10 to the desired number of top products
        model.addAttribute("bestSellingProducts", bestSellingProducts);
        return "indexAfterInscription";
    }
}
