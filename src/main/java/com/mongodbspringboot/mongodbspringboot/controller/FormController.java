package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.dto.InputIdDto;
import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
import com.mongodbspringboot.mongodbspringboot.dto.UpdateDto;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class FormController {

    private final studentInfoService studentInfoService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }

    @GetMapping("/delete")
    public String delete() {
        return "delete";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @PostMapping("/insert")
    public void postInsert(InsertDto insertDto) {
        studentInfoService.insertStudentInfo(insertDto);
    }

    @PostMapping("/delete")
    public void postDelete(InputIdDto inputIdDto) {
        studentInfoService.deleteInfoById(inputIdDto.getId());
    }

    @PostMapping("/update")
    public void postDelete(UpdateDto updateDto) {
        studentInfoService.updateStudentInfoById(updateDto.getId());
    }

    @PostMapping("/search")
    public ModelAndView postSearch(@ModelAttribute InputIdDto inputForSearchDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        if(Objects.equals(inputForSearchDto.getId(), "")) {
            modelAndView.addObject("outputFormList",
                    studentInfoService.selectAllInfo());
        } else {
            modelAndView.addObject("outputFormList",
                    studentInfoService.selectInfoById(inputForSearchDto.getId()));
        }
        return modelAndView;
    }
}
