package com.rohinikumar.collegeManagementSystem.user.repository;

import com.rohinikumar.collegeManagementSystem.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(
            String username,
            String email
    );

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByIsActiveTrue();

    Page<User> findByIsActiveTrueAndUsernameContainingIgnoreCase(
            String username,
            Pageable pageable
    );

    Page<User> findByIsActiveTrue(Pageable pageable);
}