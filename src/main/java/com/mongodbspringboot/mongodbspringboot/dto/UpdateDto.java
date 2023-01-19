package com.mongodbspringboot.mongodbspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateDto {
    private String id;
    private String grade;
    private String email;
    private String hobby;
}
