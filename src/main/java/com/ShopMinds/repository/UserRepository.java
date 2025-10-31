package com.ShopMinds.repository;

import com.ShopMinds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    // Custom finder by email
    Optional<User> findByEmail(String email);

    //Check if an email exists(return booleans)
    Boolean existsByEmail(String email);

}
