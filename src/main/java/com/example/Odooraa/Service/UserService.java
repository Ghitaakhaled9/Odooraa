package com.example.Odooraa.Service;

import com.example.Odooraa.Repository.UserRepository;
import com.example.Odooraa.entities.UserSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserSite> getAllUsers() {
        return userRepository.findAll();
    }

    public UserSite getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserSite createUser(UserSite user) {
        return userRepository.save(user);
    }

    public UserSite updateUser(Long id, UserSite userDetails) {
        UserSite user = userRepository.findById(id).orElse(null);
        if (user != null) {
            // Update user details
            return userRepository.save(user);
        }
        return null;
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        UserSite user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
