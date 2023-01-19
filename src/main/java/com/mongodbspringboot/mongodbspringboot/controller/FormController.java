package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
import com.mongodbspringboot.mongodbspringboot.dto.InputIdDto;
import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
import com.mongodbspringboot.mongodbspringboot.dto.UpdateDto;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String postInsert(InsertDto insertDto) {
        studentInfoService.insertStudentInfo(insertDto);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String postDelete(InputIdDto inputIdDto) {
        studentInfoService.deleteInfoById(inputIdDto.getId());

        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateInfoById(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            UpdateDto updateDto
    ) {
        studentInfoService.updateStudentInfoById(updateDto.getId(), searchType, searchValue);

        return "redirect:/";
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
