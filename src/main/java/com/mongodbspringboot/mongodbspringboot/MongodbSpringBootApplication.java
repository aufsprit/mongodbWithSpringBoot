package com.mongodbspringboot.mongodbspringboot;

import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class MongodbSpringBootApplication implements CommandLineRunner {

    @Autowired
    studentInfoRepository studentInfoRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodbSpringBootApplication.class, args);
    }

    public void run(String... args) {
        deleteAllInfo();
        insertStudentInfo();
        selectInfoById("2019201030");
        updateStudentInfoById("2019201030");
        selectInfoById("2019201030");
        deleteInfoById("2019201030");
        selectAllInfo();
    }

    public void insertStudentInfo() {
        studentInfoRepository.save(
                new studentInfo(
                        "2015100930",
                        "aufsprit",
                        4,
                        "male",
                        "sio3@gmail.com",
                        new Document("coll_name", "컴퓨터학부").append("dept_name", "컴퓨터학과"),
                        new String[]{"게임","코딩"}
                )
        );
        studentInfoRepository.save(
                new studentInfo(
                        "2019201030",
                        "test",
                        3,
                        "male",
                        "test@gmail.com",
                        new Document("coll_name", "test1").append("dept_name", "test2"),
                        new String[]{"testtest","test"}
                )
        );
        studentInfoRepository.save(
                new studentInfo(
                        "2020094140",
                        "saltIs",
                        4,
                        "female",
                        "saltIsSalt@gmail.com",
                        new Document("coll_name", "간호학과").append("dept_name", "소아간호전공"),
                        new String[]{"피아노연주","요리"}
                )
        );
    }

    public void selectInfoById(String id) {
        studentInfo studentInfo = studentInfoRepository.findStudentInfoById(id);
        System.out.println(getStudentInfoDetails(studentInfo));
    }

    public void selectAllInfo() {
        long count = studentInfoRepository.count();
        System.out.println("Entered number of student : " + count);
        studentInfoRepository.findAll().forEach(id -> System.out.println(getStudentInfoDetails(id)));
    }

    public void updateStudentInfoById(String id) {
        int newGrade = 2;
        String newEmail = "newEmail@mail.com";
        String[] newHobby = {"coding", "game"};

        List<studentInfo> list = studentInfoRepository.findAll(id);
        list.forEach(
                studentId ->
                {
                    studentId.setGrade(newGrade);
                    studentId.setEmail(newEmail);
                    studentId.setHobby(newHobby);
                });
        List<studentInfo> infoUpdated = studentInfoRepository.saveAll(list);
    }

    public void deleteAllInfo() {
        studentInfoRepository.deleteAll();
    }

    public void deleteInfoById(String id) {
        studentInfoRepository.deleteById(id);
    }

    public String getStudentInfoDetails(studentInfo id) {
        System.out.println(
                id.getId() + " " +
                id.getName() + " " +
                id.getGrade() + " " +
                id.getGender() + " " +
                id.getEmail() + " " +
                id.getBelong() + " " +
                Arrays.toString(id.getHobby())
        );

        return "";
    }
}
