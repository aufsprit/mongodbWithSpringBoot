package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.dto.InputIdDto;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class deleteController {

    private final studentInfoDeleteService studentInfoDeleteService;

    @GetMapping("/delete")
    public String delete() {
        return "delete";
    }

    @PostMapping("/delete")
    public String postDelete(InputIdDto inputIdDto) {
        studentInfoDeleteService.deleteInfoById(inputIdDto.getId());

        return "redirect:/";
    }

    @PostMapping("/deleteAll")
    public String postDelete() {
        studentInfoDeleteService.deleteAllInfo();

        return "redirect:/";
    }
}
