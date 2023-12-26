package com.example.watchflow.repository;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import com.example.watchflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);


}
