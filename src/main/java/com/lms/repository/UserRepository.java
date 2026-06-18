package com.lms.repository;

import com.lms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.lms.enums.Role;
import com.lms.enums.Status;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    List<User> findByRoleAndStatus(
        Role role,
        Status status
);
}