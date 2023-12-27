package com.example.watchflow.repository;

import com.example.watchflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);


    @Query(nativeQuery = true, value = "SELECT EXISTS (SELECT 1 FROM User WHERE login = ?1)")
    boolean isUserExists(String login);

    @Query(nativeQuery = true, value = "SELECT EXISTS (SELECT 1 FROM User WHERE email = ?1)")
    boolean isUserExistsByEmail(String email);
}
