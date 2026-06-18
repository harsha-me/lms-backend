package com.lms.controller;

import com.lms.dto.EnrollRequest;
import com.lms.service.EnrollmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(
            EnrollmentService enrollmentService) {

        this.enrollmentService = enrollmentService;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    public String enroll(
            @RequestBody EnrollRequest request,
            Authentication authentication) {

        return enrollmentService.enroll(
                request,
                authentication.getName()
        );
    }
    @PreAuthorize("hasRole('STUDENT')")
@GetMapping("/my-courses")
public Object myCourses(
        Authentication authentication) {

    return enrollmentService.getMyCourses(
            authentication.getName()
    );
}
}