package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class insertController {

    private final studentInfoInsertService studentInfoInsertService;

    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }

    @PostMapping("/insert")
    public String postInsert(InsertDto insertDto) {
        studentInfoInsertService.insertStudentInfo(insertDto);

        return "redirect:/";
    }
}
