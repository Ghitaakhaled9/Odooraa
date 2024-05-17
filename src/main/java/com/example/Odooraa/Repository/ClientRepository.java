package com.example.Odooraa.Repository;


import com.example.Odooraa.entities.UserSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<UserSite, Long> {
}
