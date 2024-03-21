package it.omsu.repository;

import it.omsu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_user', 'id'))", nativeQuery = true)
    Long getNextId();

    Optional<User> findUserById(String userId);

    void deleteById(String userId);
}
