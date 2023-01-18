package com.mongodbspringboot.mongodbspringboot.dto;

import lombok.Builder;
import lombok.Getter;
import org.bson.Document;

@Getter
@Builder
public class OutputForSearchDto {

    private String id;
    private String name;
    private int grade;
    private String gender;
    private String email;
    private Document belong;
    private String[] hobby;
}
