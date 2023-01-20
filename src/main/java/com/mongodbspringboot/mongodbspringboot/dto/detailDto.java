package com.mongodbspringboot.mongodbspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class detailDto {
    private String id;
    private String name;
    private String grade;
    private String belong;
    private String hobby;
}
