package com.lms.controller;

import com.lms.dto.LoginRequest;
import com.lms.dto.StudentSignupRequest;
import com.lms.dto.TeacherSignupRequest;
import com.lms.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.lms.dto.LoginResponse;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/student/register")
    public String registerStudent(
            @Valid @RequestBody StudentSignupRequest request) {

        return authService.registerStudent(request);
    }

    @PostMapping("/teacher/register")
    public String registerTeacher(
            @Valid @RequestBody TeacherSignupRequest request) {

        return authService.registerTeacher(request);
    }
    @PostMapping("/login")
public LoginResponse login(
        @Valid @RequestBody LoginRequest request) {

    System.out.println("LOGIN API HIT");

    return authService.login(
            request.getEmail(),
            request.getPassword()
    );
}
}