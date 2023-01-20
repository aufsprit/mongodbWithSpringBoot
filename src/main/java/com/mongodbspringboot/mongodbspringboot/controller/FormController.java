package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
import com.mongodbspringboot.mongodbspringboot.dto.InputIdDto;
import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
import com.mongodbspringboot.mongodbspringboot.dto.UpdateDto;
import com.mongodbspringboot.mongodbspringboot.dto.detailDto;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
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

    @GetMapping("/detail")
    public String detail() {
        return "detail";
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

    @PostMapping("/deleteAll")
    public String postDelete() {
        studentInfoService.deleteAllInfo();

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

    @PostMapping("/conditional")
    public ModelAndView postSearchWithKeyword(
            @ModelAttribute detailDto detailDto,
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        String keyword = String.valueOf(searchType);
        if(searchValue.equals("")) {
            modelAndView.addObject("count",
                    studentInfoService.countAllInfo());
            modelAndView.addObject("outputFormList",
                    studentInfoService.selectAllInfo());
        } else {
            if ("NAME".equals(keyword)) {
                modelAndView.addObject("count",
                        studentInfoService.countInfoByName(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoService.selectInfoByName(searchValue));
            } else if ("GRADE".equals(keyword)) {
                modelAndView.addObject("count",
                        studentInfoService.countInfoByGrade(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoService.selectInfoByGrade(Integer.parseInt(searchValue)));
            } else if ("BELONG".equals(keyword)) {
                modelAndView.addObject("count",
                        studentInfoService.countInfoByBelong(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoService.selectInfoByBelong(Document.parse(searchValue)));
            } else if ("HOBBY".equals(keyword)) {
                modelAndView.addObject("count",
                        studentInfoService.countInfoByHobby(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoService.selectInfoByHobby(searchValue));
            }
        }
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView postSearch(@ModelAttribute InputIdDto inputForSearchDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        if(Objects.equals(inputForSearchDto.getId(), "")) {
            modelAndView.addObject("count",
                    studentInfoService.countAllInfo());
            modelAndView.addObject("outputFormList",
                    studentInfoService.selectAllInfo());
        } else {
            modelAndView.addObject("count",
                    studentInfoService.countInfoById(inputForSearchDto.getId());
            modelAndView.addObject("outputFormList",
                    studentInfoService.selectInfoById(inputForSearchDto.getId()));
        }
        return modelAndView;
    }
}
