package com.lms.service;

import com.lms.dto.StudentSignupRequest;
import com.lms.dto.TeacherSignupRequest;
import com.lms.entity.User;
import com.lms.enums.Role;
import com.lms.enums.Status;
import com.lms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
   public AuthService(UserRepository userRepository,
                   BCryptPasswordEncoder passwordEncoder,
                   JwtService jwtService) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
}

    public String registerStudent(StudentSignupRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            return "Email already exists";
        }

        User student = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .status(Status.APPROVED)
                .build();

        userRepository.save(student);

        return "Student Registered Successfully";
    }

    public String registerTeacher(TeacherSignupRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            return "Email already exists";
        }

        User teacher = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.TEACHER)
                .status(Status.PENDING)
                .build();

        userRepository.save(teacher);

        return "Teacher Registration Submitted For Approval";
    }
    public String login(String email, String password) {

    Optional<User> optionalUser =
            userRepository.findByEmail(email);

    if(optionalUser.isEmpty()) {
        return "Invalid Credentials";
    }

    User user = optionalUser.get();

    if(!passwordEncoder.matches(password, user.getPassword())) {
        return "Invalid Credentials";
    }

    if(user.getStatus() != Status.APPROVED) {
        return "Account Not Approved Yet";
    }   

    return jwtService.generateToken(
        user.getEmail(),
        user.getRole().name()
);  
}
}