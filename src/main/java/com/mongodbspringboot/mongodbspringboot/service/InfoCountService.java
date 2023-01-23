package com.mongodbspringboot.mongodbspringboot.service;

import com.mongodbspringboot.mongodbspringboot.repository.studentInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfoCountService {

    private final studentInfoRepository studentInfoRepository;

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
        try {
            Integer.parseInt(keyword);
            return studentInfoRepository.countByGrade(Integer.parseInt(keyword));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public long countInfoByHobby(String keyword) {
        return studentInfoRepository.countByHobbyContains(keyword);
    }
}
