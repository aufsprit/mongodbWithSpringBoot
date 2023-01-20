package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
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

    public List<studentInfo> selectInfoByName(String name) {
        return studentInfoRepository.findStudentInfoByName(name);
    }

    public List<studentInfo> selectInfoByGrade(int grade) {
        return studentInfoRepository.findStudentInfoByGrade(grade);
    }

    public List<studentInfo> selectInfoByBelong(Document belong) {
        return studentInfoRepository.findStudentInfoByBelongContains(belong);
    }

    public List<studentInfo> selectInfoByHobby(String hobby) {
        return studentInfoRepository.findStudentInfoByHobbyContains(hobby);
    }

    public List<studentInfo> selectAllInfo() {
        return studentInfoRepository.findAll();
    }

    public long countAllInfo() {
        return studentInfoRepository.count();
    }
    public long countInfoById(String keyword) {
        return studentInfoRepository.countById(keyword);
    }
    public long countInfoByName(String keyword) {
        return studentInfoRepository.countByName(keyword);
    }
    public long countInfoByGrade(String keyword) {
        return studentInfoRepository.countByGrade(Integer.parseInt(keyword));
    }
    public long countInfoByBelong(String keyword) {
        return studentInfoRepository.countByBelongContains(Document.parse(keyword));
    }
    public long countInfoByHobby(String keyword) {
        return studentInfoRepository.countByHobbyContains(keyword);
    }

    public void updateStudentInfoById(String id, SearchType searchType, String searchKeyword) {
        if(searchKeyword == null || searchKeyword.isBlank()) {
            log.error("키워드가 존재하지 않음");
            return;
        }
        String[] hobby = searchKeyword.split(" ");
        List<studentInfo> list = studentInfoRepository.findAll(id);
        switch (searchType) {
            case GRADE -> list.forEach(info -> info.setGrade(Integer.parseInt(searchKeyword)));
            case EMAIL -> list.forEach(info -> info.setEmail(searchKeyword));
            case HOBBY -> list.forEach(info -> info.setHobby(hobby));
        }
        List<studentInfo> infoUpdated = studentInfoRepository.saveAll(list);
    }

    public void deleteAllInfo() {
        studentInfoRepository.deleteAll();
    }

    public void deleteInfoById(String id) {
        studentInfoRepository.deleteById(id);
    }
}
