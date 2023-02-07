package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class studentInfoUpdateService {

    private final studentInfoRepository studentInfoRepository;

    public void updateStudentInfoById(String id, SearchType searchType, String searchKeyword) {
        if(searchKeyword == null || searchKeyword.isEmpty()) {
            log.error("키워드가 존재하지 않음");
            return;
        }
        String[] hobby = searchKeyword.split(" ");
        List<studentInfo> list = studentInfoRepository.findAll(id);
        switch (searchType) {
            case GRADE : list.forEach(info -> info.setGrade(Integer.parseInt(searchKeyword)));
            case EMAIL : list.forEach(info -> info.setEmail(searchKeyword));
            case HOBBY : list.forEach(info -> info.setHobby(hobby));
        }
        List<studentInfo> infoUpdated = studentInfoRepository.saveAll(list);
    }
}
