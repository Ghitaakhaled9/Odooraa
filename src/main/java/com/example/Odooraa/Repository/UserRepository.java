package com.example.Odooraa.Repository;

import com.example.Odooraa.entities.UserSite;
import com.example.Odooraa.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserSite, Long> {
    UserSite findByEmail(String email);

    Object findByType(UserType client);

    long countByType(UserType userType);


    List<UserSite> findAllByType(UserType userType);
}
