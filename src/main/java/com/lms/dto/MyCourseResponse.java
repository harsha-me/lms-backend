package com.lms.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MyCourseResponse {

    private String courseTitle;
    private String teacherName;
    private Double price;
    private LocalDateTime enrolledAt;
}