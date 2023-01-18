package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
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

    public void insertStudentInfo(InsertDto insertDto) {
        String[] toBelong = insertDto.getBelong().split(" ");
        Document belong = new Document();
        belong.append("coll_name", toBelong[0]).append("dept_name", toBelong[1]);
        String[] hobby = insertDto.getHobby().split(" ");
        studentInfoRepository.save(
                new studentInfo(
                        insertDto.getId(),
                        insertDto.getName(),
                        insertDto.getGrade(),
                        insertDto.getGender(),
                        insertDto.getEmail(),
                        belong,
                        hobby
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
}
