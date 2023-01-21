package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
import com.mongodbspringboot.mongodbspringboot.dto.InputIdDto;
import com.mongodbspringboot.mongodbspringboot.dto.InsertDto;
import com.mongodbspringboot.mongodbspringboot.dto.UpdateDto;
import com.mongodbspringboot.mongodbspringboot.dto.detailDto;
import com.mongodbspringboot.mongodbspringboot.service.*;
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

    private final InfoCountService infoCountService;
    private final studentInfoDeleteService studentInfoDeleteService;
    private final studentInfoSelectService studentInfoSelectService;
    private final studentInfoInsertService studentInfoInsertService;
    private final studentInfoUpdateService studentInfoUpdateService;

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
        studentInfoInsertService.insertStudentInfo(insertDto);

        return "redirect:/";
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

    @PostMapping("/update")
    public String updateInfoById(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            UpdateDto updateDto
    ) {
        studentInfoUpdateService.updateStudentInfoById(updateDto.getId(), searchType, searchValue);

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
                    infoCountService.countAllInfo());
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectAllInfo());
        } else {
            if ("NAME".equals(keyword)) {
                modelAndView.addObject("count",
                        infoCountService.countInfoByName(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoSelectService.selectInfoByName(searchValue));
            } else if ("GRADE".equals(keyword)) {
                modelAndView.addObject("count",
                        infoCountService.countInfoByGrade(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoSelectService.selectInfoByGrade(searchValue));
            } else if ("BELONG".equals(keyword)) {
                modelAndView.addObject("count",
                infoCountService.countInfoByBelong(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoSelectService.selectInfoByBelong(searchValue));
            } else if ("HOBBY".equals(keyword)) {
                modelAndView.addObject("count",
                        infoCountService.countInfoByHobby(searchValue));
                modelAndView.addObject("outputFormList",
                        studentInfoSelectService.selectInfoByHobby(searchValue));
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
                    infoCountService.countAllInfo());
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectAllInfo());
        } else {
            modelAndView.addObject("count",
                    infoCountService.countInfoById(inputForSearchDto.getId()));
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoById(inputForSearchDto.getId()));
        }
        return modelAndView;
    }
}
