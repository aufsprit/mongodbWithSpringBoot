package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.dto.InputForSearchDto;
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

    @PostMapping("/search")
    public ModelAndView postDirection(@ModelAttribute InputForSearchDto inputForSearchDto) {
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
