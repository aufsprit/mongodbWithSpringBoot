package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class studentInfoInsertService {

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
}
