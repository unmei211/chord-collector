package it.omsu.repository;

import it.omsu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByUsername (String username);
    Optional<User> findById(String userId);
}
