package com.lms.controller;

import com.lms.dto.CreateCourseRequest;
import com.lms.dto.UpdateCourseRequest;
import com.lms.entity.Course;
import com.lms.service.CourseService;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.lms.dto.UpdateCourseRequest;
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(
            CourseService courseService) {

        this.courseService = courseService;
    }
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public String createCourse(
            @Valid @RequestBody CreateCourseRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return courseService.createCourse(
                request,
                email);
    }
    @GetMapping
public List<Course> getAllCourses() {
    return courseService.getAllCourses();
}
@PreAuthorize("hasRole('TEACHER')")
@GetMapping("/my-courses")
public Object myCourses(
        Authentication authentication) {

    return courseService.getMyCourses(
            authentication.getName()
    );
}
@PreAuthorize("hasRole('TEACHER')")
@PutMapping("/{id}")
public String updateCourse(
        @PathVariable Long id,
        @RequestBody UpdateCourseRequest request,
        Authentication authentication) {

    return courseService.updateCourse(
            id,
            request,
            authentication.getName()
    );
}
@PreAuthorize("hasRole('TEACHER')")
@DeleteMapping("/{id}")
public String deleteCourse(
        @PathVariable Long id,
        Authentication authentication) {

    return courseService.deleteCourse(
            id,
            authentication.getName()
    );
}
}