package com.mongodbspringboot.mongodbspringboot.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("testCol") // collection name
public class studentInfo {

    @Id
    private String id;

    private String name;
    private int grade;
    private String gender;
    private String email;
    private org.bson.Document belong;
    private String[] hobby;

    public studentInfo(String id, String name, int grade, String gender, String email, org.bson.Document belong, String[] hobby) {
        super();
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.gender = gender;
        this.email = email;
        this.belong = belong;
        this.hobby = hobby;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public org.bson.Document getBelong() { return belong; }
    public void setBelong(org.bson.Document belong) { this.belong = belong; }
    public String[] getHobby() { return hobby; }
    public void setHobby(String[] hobby) { this.hobby = hobby; }
}
