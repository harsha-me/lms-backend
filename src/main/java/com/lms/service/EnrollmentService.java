package com.lms.service;

import com.lms.dto.EnrollRequest;
import com.lms.dto.MyCourseResponse;
import com.lms.entity.Course;
import com.lms.entity.Enrollment;
import com.lms.entity.User;
import com.lms.repository.CourseRepository;
import com.lms.repository.EnrollmentRepository;
import com.lms.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public String enroll(
            EnrollRequest request,
            String email) {

        User student = userRepository
                .findByEmail(email)
                .orElseThrow();

        Course course = courseRepository
                .findById(request.getCourseId())
                .orElseThrow();
        if(enrollmentRepository.existsByStudentAndCourse(
        student,
        course)) {

    return "Already Enrolled";
}

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollment);

        return "Enrollment Successful";
    }
    public List<MyCourseResponse> getMyCourses(String email){

    User student = userRepository
        .findByEmail(email)
        .orElseThrow();

return enrollmentRepository
        .findByStudent(student)
        .stream()
        .map(enrollment -> MyCourseResponse.builder()
                .courseTitle(
                        enrollment.getCourse().getTitle())
                .teacherName(
                        enrollment.getCourse()
                                .getTeacher()
                                .getName())
                .price(
                        enrollment.getCourse().getPrice())
                .enrolledAt(
                        enrollment.getEnrolledAt())
                .build())
        .toList();
}
}