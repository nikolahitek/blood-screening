package com.sorsix.bloodscreening.repository;

import com.sorsix.bloodscreening.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    List<User> findAllByRole(String role);

    boolean existsByUsername(String name);

    Optional<User> findByUsername(String name);

    void deleteByUsername(String username);
}
