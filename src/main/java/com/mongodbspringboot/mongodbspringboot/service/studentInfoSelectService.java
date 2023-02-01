package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return studentInfoRepository.findStudentInfoByGrade(Integer.parseInt(grade));
    }

    public List<studentInfo> selectInfoByDept_name(String belong) {
        return studentInfoRepository.findStudentInfoByBelong(belong);
    }

    public List<studentInfo> selectInfoByHobby(String[] hobby) {
        return studentInfoRepository.findStudentInfoByHobbyAll(hobby);
    }

    public List<studentInfo> selectAllInfo() {
        return studentInfoRepository.findAll();
    }

    public List<studentInfo> selectInfoByNameAndGradeAndBelongAndHobby(String name, String grade, String belong, String[] hobby) {
        return studentInfoRepository.findStudentInfoByNameAndGradeAndBelongAndHobbyContains(name, Integer.parseInt(grade), belong, hobby);
    }

    public List<studentInfo> selectInfoByNameAndGradeAndBelong(String name, String grade, String belong) {
        return studentInfoRepository.findStudentInfoByNameAndGradeAndBelong(name, Integer.parseInt(grade), belong);
    }

    public List<studentInfo> selectInfoByNameAndGradeAndHobby(String name, String grade, String[] hobby) {
        return studentInfoRepository.findStudentInfoByNameAndGradeAndHobbyContains(name, Integer.parseInt(grade), hobby);
    }

    public List<studentInfo> selectInfoByNameAndBelongAndHobby(String name, String belong, String[] hobby) {
        return studentInfoRepository.findStudentInfoByNameAndBelongAndHobbyContains(name, belong, hobby);
    }

    public List<studentInfo> selectInfoByGradeAndBelongAndHobby(String grade, String belong, String[] hobby) {
        return studentInfoRepository.findStudentInfoByGradeAndBelongAndHobbyContains(Integer.parseInt(grade), belong, hobby);
    }

    public List<studentInfo> selectInfoByNameAndGrade(String name, String grade) {
        return studentInfoRepository.findStudentInfoByNameAndGrade(name, Integer.parseInt(grade));
    }

    public List<studentInfo> selectInfoByNameAndBelong(String name, String belong) {
        return studentInfoRepository.findStudentInfoByNameAndBelong(name, belong);
    }

    public List<studentInfo> selectInfoByNameAndHobby(String name, String[] hobby) {
        return studentInfoRepository.findStudentInfoByNameAndHobbyContains(name, hobby);
    }

    public List<studentInfo> selectInfoByGradeAndBelong(String grade, String belong) {
        return studentInfoRepository.findStudentInfoByGradeAndBelong(Integer.parseInt(grade), belong);
    }

    public List<studentInfo> selectInfoByGradeAndHobby(String grade, String[] hobby) {
        return studentInfoRepository.findStudentInfoByGradeAndHobbyContains(Integer.parseInt(grade), hobby);
    }

    public List<studentInfo> selectInfoByBelongAndHobby(String belong, String[] hobby) {
        return studentInfoRepository.findStudentInfoByBelongAndHobbyContains(belong, hobby);
    }
}
