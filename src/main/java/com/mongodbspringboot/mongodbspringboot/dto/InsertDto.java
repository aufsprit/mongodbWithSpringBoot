package com.mongodbspringboot.mongodbspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InsertDto {
    private String id;
    private String name;
    private int grade;
    private String gender;
    private String email;
    private String belong;
    private String hobby;
}
