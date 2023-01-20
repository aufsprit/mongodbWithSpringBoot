package com.mongodbspringboot.mongodbspringboot.repository;

import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface studentInfoRepository extends MongoRepository<studentInfo, String> {

    @Query("{id: '?0'}")
    studentInfo findStudentInfoById(String id);

    public long count();

    public long countById(String id);

    @Query("{id: '?0'}")
    List<studentInfo> findAll(String id);
}
