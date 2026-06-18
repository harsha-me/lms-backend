package com.lms.repository;

import com.lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lms.entity.User;
import java.util.List;
public interface CourseRepository
        extends JpaRepository<Course, Long> {
            List<Course> findByTeacher(User teacher);
}