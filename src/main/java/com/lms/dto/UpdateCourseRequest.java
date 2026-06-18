package com.lms.dto;

import lombok.Data;

@Data
public class UpdateCourseRequest {

    private String title;
    private String description;
    private Double price;
}