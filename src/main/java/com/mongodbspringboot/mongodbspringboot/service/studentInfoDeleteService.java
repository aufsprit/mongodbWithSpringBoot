package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class studentInfoDeleteService {

    private final studentInfoRepository studentInfoRepository;

    public void deleteAllInfo() {
        studentInfoRepository.deleteAll();
    }

    public void deleteInfoById(String id) {
        studentInfoRepository.deleteById(id);
    }
}
