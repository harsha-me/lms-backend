package com.lms.service;

import com.lms.entity.User;
import com.lms.enums.Role;
import com.lms.enums.Status;
import com.lms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        if (!userRepository.existsByEmail("admin@lms.com")) {

            User admin = User.builder()
                    .name("System Admin")
                    .email("admin@lms.com")
                    .password("Admin@123")
                    .role(Role.ADMIN)
                    .status(Status.APPROVED)
                    .build();

            userRepository.save(admin);

            System.out.println("Admin account created");
        }
    }
}