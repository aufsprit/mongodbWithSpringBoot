package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
import com.mongodbspringboot.mongodbspringboot.dto.UpdateDto;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class updateController {

    private final studentInfoUpdateService studentInfoUpdateService;

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @PostMapping("/update")
    public String updateInfoById(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            UpdateDto updateDto
    ) {
        studentInfoUpdateService.updateStudentInfoById(updateDto.getId(), searchType, searchValue);

        return "redirect:/";
    }
}
