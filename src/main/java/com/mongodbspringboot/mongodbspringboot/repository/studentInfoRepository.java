package com.mongodbspringboot.mongodbspringboot.repository;

import com.mongodbspringboot.mongodbspringboot.model.studentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface studentInfoRepository extends MongoRepository<studentInfo, String> {

    @Query("{id: '?0'}")
    studentInfo findStudentInfoById(String id);

    @Query("{name: '?0'}")
    List<studentInfo> findStudentInfoByName(String name);

    @Query("{grade: '?0'}")
    List<studentInfo> findStudentInfoByGrade(int grade);

    @Query("{'belong.dept_name': '?0'}")
    List<studentInfo> findStudentInfoByBelong(String belong);

    @Query("{hobby: '?0'}")
    List<studentInfo> findStudentInfoByHobbyContains(String hobby);

    public long count();
    public long countById(String id);
    public long countByName(String name);
    public long countByGrade(int grade);
    public long countByHobbyContains(String hobby);

    @Query("{id: '?0'}")
    List<studentInfo> findAll(String id);
}
