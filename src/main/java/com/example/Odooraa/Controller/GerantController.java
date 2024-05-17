package com.example.Odooraa.Controller;


import com.example.Odooraa.Repository.UserRepository;

import com.example.Odooraa.Service.UserService;

import com.example.Odooraa.entities.UserSite;
import com.example.Odooraa.entities.UserType;
import com.example.Odooraa.entities.sexe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GerantController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/gerant")
    public String afficherUsers(Model model) {
        List<UserSite> adminUsers = userRepository.findAllByType(UserType.ADMIN);
        List<UserSite> gerantUsers = userRepository.findAllByType(UserType.GERANT);

        // Combine the lists if necessary
        List<UserSite> utilisateurs = new ArrayList<>();
        utilisateurs.addAll(adminUsers);
        utilisateurs.addAll(gerantUsers);

        model.addAttribute("utilisateurs", utilisateurs);
        return "gerant";
    }

    @PostMapping("/gerant/add")
    public String ajouterGerant(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("sexe") sexe sexe, // Change the type to Sexe enum
            @RequestParam("adresse") String adresse,
            @RequestParam("tel") String tel,
            @RequestParam("email") String email,
            @RequestParam("Type") UserType type
    ) {
        UserSite user = new UserSite();
        user.setUsername(username);
        user.setPassword(password);
        user.setSexe(sexe); // Set the enum value directly
        user.setAdresse(adresse);
        user.setTel(tel);
        user.setEmail(email);
        user.setType(type);

        userRepository.save(user);

        return "redirect:/gerant"; // Redirection après l'ajout
    }

    @GetMapping("/gerant/delete/{id}")
    public String deleteGerant(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/gerant";
    }



    // Endpoint pour mettre à jour les données de l'utilisateur
    @PostMapping("/gerant/updateUser/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserSite updatedUser) {
        UserSite existingUser = userService.getUserById(userId);
        if (existingUser != null) {
            // Mettre à jour les champs de l'utilisateur existant avec les nouvelles données
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            if (StringUtils.isEmpty(existingUser.getSexe())) {
                // Handle the empty sexe value here, such as providing a default value or skipping it
                existingUser.setSexe(sexe.DEFAULT); // Example: setting a default value
            }
            existingUser.setAdresse(updatedUser.getAdresse());
            existingUser.setTel(updatedUser.getTel());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setType(updatedUser.getType());

            // Enregistrer les modifications dans la base de données
            userRepository.save(existingUser);

            return new ResponseEntity<>("Les modifications ont été enregistrées avec succès !", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Utilisateur non trouvé avec l'identifiant: " + userId, HttpStatus.NOT_FOUND);
        }
    }
    // Define the updateUser method



    @GetMapping("/gerant/getUser/{userId}")
    public ResponseEntity<UserSite> getUser(@PathVariable Long userId) {
        UserSite user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
