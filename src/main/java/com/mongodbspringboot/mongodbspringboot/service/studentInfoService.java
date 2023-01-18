package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class studentInfoService {

    private final studentInfoRepository studentInfoRepository;

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

    public List<studentInfo> selectInfoById(String id) {
        studentInfo studentInfo = studentInfoRepository.findStudentInfoById(id);
        return Collections.singletonList(studentInfo);
    }

    public List<studentInfo> selectAllInfo() {
        /*long count = studentInfoRepository.count();
        System.out.println("Entered number of student : " + count);*/
        return studentInfoRepository.findAll();
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
                        id.getBelong() + " " /*+
                        Arrays.toString(id.getHobby())*/
        );

        return "";
    }

}
