package com.lms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherCourseResponse {

    private Long id;
    private String title;
    private String description;
    private Double price;
}