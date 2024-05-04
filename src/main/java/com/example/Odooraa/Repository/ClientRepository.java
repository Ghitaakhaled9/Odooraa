package com.example.demo.Repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<User, Long> {
}
