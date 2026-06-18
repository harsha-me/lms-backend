package com.lms.controller;

import com.lms.entity.User;
import com.lms.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/teachers/pending")
    public List<User> getPendingTeachers() {
        return adminService.getPendingTeachers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/teachers/{id}/approve")
    public String approveTeacher(@PathVariable Long id) {
        return adminService.approveTeacher(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/teachers/{id}/reject")
    public String rejectTeacher(@PathVariable Long id) {
        return adminService.rejectTeacher(id);
    }
}