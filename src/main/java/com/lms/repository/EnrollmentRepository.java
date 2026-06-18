package com.lms.repository;

import com.lms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.entity.User;
import java.util.List;
import com.lms.entity.Course;
import com.lms.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {
            List<Enrollment> findByStudent(User student);
            boolean existsByStudentAndCourse(
        User student,
        Course course
);
@Modifying
void deleteByCourse(Course course);
}