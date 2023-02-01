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

    @Query("{grade: ?0}")
    List<studentInfo> findStudentInfoByGrade(int grade);

    @Query("{'belong.dept_name': '?0'}")
    List<studentInfo> findStudentInfoByBelong(String belong);

    @Query("{'hobby': {$all: ?0}}")
    List<studentInfo> findStudentInfoByHobbyAll(String[] hobby);

    @Query("{name: '?0', grade: ?1, 'belong.dept_name': '?2', 'hobby': {$all: ?3}}")
    List<studentInfo> findStudentInfoByNameAndGradeAndBelongAndHobbyContains(String name, int grade, String belong, String[] hobby);

    @Query("{name: '?0', grade: ?1, 'belong.dept_name': '?2'}")
    List<studentInfo> findStudentInfoByNameAndGradeAndBelong(String name, int grade, String belong);

    @Query("{name: '?0', grade: ?1, 'hobby': {$all: ?2}}")
    List<studentInfo> findStudentInfoByNameAndGradeAndHobbyContains(String name, int grade, String[] hobby);

    @Query("{name: '?0', 'belong.dept_name': '?1', 'hobby': {$all: ?2}}")
    List<studentInfo> findStudentInfoByNameAndBelongAndHobbyContains(String name, String belong, String[] hobby);

    @Query("{grade: ?0, 'belong.dept_name': '?1', 'hobby': {$all: ?2}}")
    List<studentInfo> findStudentInfoByGradeAndBelongAndHobbyContains(int grade, String belong, String[] hobby);

    @Query("{name: '?0', grade: ?1}")
    List<studentInfo> findStudentInfoByNameAndGrade(String name, int grade);

    @Query("{name: '?0', 'belong.dept_name': '?1'}")
    List<studentInfo> findStudentInfoByNameAndBelong(String name, String belong);

    @Query("{name: '?0', 'hobby': {$all: ?1}}")
    List<studentInfo> findStudentInfoByNameAndHobbyContains(String name, String[] hobby);

    @Query("{grade: ?0, 'belong.dept_name': '?1'}")
    List<studentInfo> findStudentInfoByGradeAndBelong(int grade, String belong);

    @Query("{grade: ?0, 'hobby': {$all: ?1}}")
    List<studentInfo> findStudentInfoByGradeAndHobbyContains(int grade, String[] hobby);

    @Query("{'belong.dept_name': '?0', 'hobby': {$all: ?1}}")
    List<studentInfo> findStudentInfoByBelongAndHobbyContains(String belong, String[] hobby);

    public long count();
    public long countById(String id);
    public long countByName(String name);
    public long countByGrade(int grade);

    @Query("{id: '?0'}")
    List<studentInfo> findAll(String id);
}
