package com.lms.service;

import com.lms.entity.User;
import com.lms.enums.Role;
import com.lms.enums.Status;
import com.lms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getPendingTeachers() {

        return userRepository
                .findByRoleAndStatus(
                        Role.TEACHER,
                        Status.PENDING
                );
    }

    public String approveTeacher(Long id) {

        User teacher = userRepository
                .findById(id)
                .orElseThrow();

        teacher.setStatus(Status.APPROVED);

        userRepository.save(teacher);

        return "Teacher Approved";
    }

    public String rejectTeacher(Long id) {

        User teacher = userRepository
                .findById(id)
                .orElseThrow();

        teacher.setStatus(Status.REJECTED);

        userRepository.save(teacher);

        return "Teacher Rejected";
    }
}