package com.example.Odooraa.Repository;

import com.example.Odooraa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
