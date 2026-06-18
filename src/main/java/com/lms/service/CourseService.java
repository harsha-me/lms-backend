package com.lms.service;

import com.lms.dto.CreateCourseRequest;
import com.lms.dto.TeacherCourseResponse;
import com.lms.dto.UpdateCourseRequest;
import com.lms.entity.Course;
import com.lms.entity.User;
import com.lms.repository.CourseRepository;
import com.lms.repository.EnrollmentRepository;
import com.lms.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.lms.dto.TeacherCourseResponse;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
     private final EnrollmentRepository enrollmentRepository;
    
   
    public CourseService(
        CourseRepository courseRepository,
        UserRepository userRepository,
        EnrollmentRepository enrollmentRepository) {

    this.courseRepository = courseRepository;
    this.userRepository = userRepository;
    this.enrollmentRepository = enrollmentRepository;
}
    public String createCourse(
            CreateCourseRequest request,
            String email) {

        User teacher = userRepository
                .findByEmail(email)
                .orElseThrow();

        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .teacher(teacher)
                .build();

        courseRepository.save(course);

        return "Course Created Successfully";
    }
    public List<Course> getAllCourses() {
    return courseRepository.findAll();
}
public List<TeacherCourseResponse> getMyCourses(
        String email) {

    User teacher = userRepository
            .findByEmail(email)
            .orElseThrow();

    return courseRepository
            .findByTeacher(teacher)
            .stream()
            .map(course ->
                    TeacherCourseResponse.builder()
                            .id(course.getId())
                            .title(course.getTitle())
                            .description(course.getDescription())
                            .price(course.getPrice())
                            .build())
            .toList();
}
public String updateCourse(
        Long courseId,
        UpdateCourseRequest request,
        String email) {

    User teacher = userRepository
            .findByEmail(email)
            .orElseThrow();

    Course course = courseRepository
            .findById(courseId)
            .orElseThrow();

    if(!course.getTeacher()
            .getId()
            .equals(teacher.getId())) {

        return "You can update only your courses";
    }

    course.setTitle(request.getTitle());
    course.setDescription(request.getDescription());
    course.setPrice(request.getPrice());

    courseRepository.save(course);

    return "Course Updated Successfully";
}
@Transactional
public String deleteCourse(
        Long courseId,
        String email) {

    User teacher = userRepository
            .findByEmail(email)
            .orElseThrow();

    Course course = courseRepository
            .findById(courseId)
            .orElseThrow();

    if(!course.getTeacher()
            .getId()
            .equals(teacher.getId())) {

        return "You can delete only your courses";
    }

    enrollmentRepository.deleteByCourse(course);

courseRepository.delete(course);

    return "Course Deleted Successfully";
}
}