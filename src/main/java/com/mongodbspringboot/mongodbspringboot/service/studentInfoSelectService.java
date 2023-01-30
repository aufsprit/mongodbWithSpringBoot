package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class studentInfoSelectService {

    private final studentInfoRepository studentInfoRepository;

    public List<studentInfo> selectInfoById(String id) {
        studentInfo studentInfo = studentInfoRepository.findStudentInfoById(id);
        return Collections.singletonList(studentInfo);
    }

    public List<studentInfo> selectInfoByName(String name) {
        return studentInfoRepository.findStudentInfoByName(name);
    }

    public List<studentInfo> selectInfoByGrade(String grade) {
        try {
            Integer.parseInt(grade);
            return studentInfoRepository.findStudentInfoByGrade(Integer.parseInt(grade));
        } catch (NumberFormatException e) {
            log.error("숫자가 아닙니다.");
            return null;
        }
    }

    public List<studentInfo> selectInfoByDept_name(String belong) {
        return studentInfoRepository.findStudentInfoByBelong(belong);
    }

    public List<studentInfo> selectInfoByHobby(String hobby) {
        String[] Hobby = hobby.split(" ");
        for (String s : Hobby) {
            return studentInfoRepository.findStudentInfoByHobbyContains(s);
        }
        return null;
    }

    public List<studentInfo> selectAllInfo() {
        return studentInfoRepository.findAll();
    }

}
