package com.mongodbspringboot.mongodbspringboot.controller;

import com.mongodbspringboot.mongodbspringboot.domain.constant.SearchType;
import com.mongodbspringboot.mongodbspringboot.dto.CompoundDto;
import com.mongodbspringboot.mongodbspringboot.dto.InputIdDto;
import com.mongodbspringboot.mongodbspringboot.service.InfoCountService;
import com.mongodbspringboot.mongodbspringboot.service.studentInfoSelectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FormController {

    private final InfoCountService infoCountService;
    private final studentInfoSelectService studentInfoSelectService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/detail")
    public String detail() {
        return "detail";
    }

    @PostMapping("/conditional")
    public ModelAndView postSearchWithKeyword(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        String keyword = String.valueOf(searchType);
        if (keyword.equals("GRADE")) {
            try {
                Integer.parseInt(searchValue);
            } catch (NumberFormatException e) {
                log.error("숫자가 아닙니다.");
                return null;
            }
        }
        if(searchValue.equals("")) {
            modelAndView.addObject("count",
                    infoCountService.countAllInfo());
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectAllInfo());
        } else {
            switch (keyword) {
                case "NAME" : {
                    modelAndView.addObject("count",
                            infoCountService.countInfoByName(searchValue));
                    modelAndView.addObject("outputFormList",
                            studentInfoSelectService.selectInfoByName(searchValue));
                }
                case "GRADE" : {
                    modelAndView.addObject("count",
                            infoCountService.countInfoByGrade(searchValue));
                    modelAndView.addObject("outputFormList",
                            studentInfoSelectService.selectInfoByGrade(searchValue));
                }
                case "BELONG" : {
                    modelAndView.addObject("count",
                            infoCountService.countInfoByBelong(searchValue));
                    modelAndView.addObject("outputFormList",
                            studentInfoSelectService.selectInfoByDept_name(searchValue));
                }
                case "HOBBY" : {
                    String[] hobby = searchValue.split(" ");
                    modelAndView.addObject("outputFormList",
                            studentInfoSelectService.selectInfoByHobby(hobby));
                    modelAndView.addObject("count",
                            infoCountService.countInfoAny(modelAndView.toString()));
                }
            }
        }
        return modelAndView;
    }

    @PostMapping("/compound")
    public ModelAndView postSearchCompound(@ModelAttribute CompoundDto compoundDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        if (!compoundDto.getGrade().equals("")) {
            try {
                Integer.parseInt(compoundDto.getGrade());
            } catch (NumberFormatException e) {
                log.error("숫자가 아닙니다.");
                return null;
            }
        }
        String[] hobby = compoundDto.getHobby().split(" ");
        if (!compoundDto.getName().equals("")&&
                !compoundDto.getGrade().equals("")&&
                !compoundDto.getBelong().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndGradeAndBelongAndHobby(
                            compoundDto.getName(),
                            compoundDto.getGrade(),
                            compoundDto.getBelong(),
                            hobby));
        } else if (!compoundDto.getName().equals("")&&
                !compoundDto.getGrade().equals("")&&
                !compoundDto.getBelong().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndGradeAndBelong(
                            compoundDto.getName(),
                            compoundDto.getGrade(),
                            compoundDto.getBelong()));
        } else if (!compoundDto.getName().equals("")&&
                !compoundDto.getGrade().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndGradeAndHobby(
                            compoundDto.getName(),
                            compoundDto.getGrade(),
                            hobby));
        } else if (!compoundDto.getName().equals("")&&
                !compoundDto.getBelong().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndBelongAndHobby(
                            compoundDto.getName(),
                            compoundDto.getBelong(),
                            hobby));
        } else if (!compoundDto.getGrade().equals("")&&
                !compoundDto.getBelong().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByGradeAndBelongAndHobby(
                            compoundDto.getGrade(),
                            compoundDto.getBelong(),
                            hobby));
        } else if (!compoundDto.getName().equals("")&&
                !compoundDto.getGrade().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndGrade(
                            compoundDto.getName(),
                            compoundDto.getGrade()));
        } else if (!compoundDto.getName().equals("")&&
                !compoundDto.getBelong().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndBelong(
                            compoundDto.getName(),
                            compoundDto.getBelong()));
        } else if (!compoundDto.getName().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByNameAndHobby(
                            compoundDto.getName(),
                            hobby));
        } else if (!compoundDto.getGrade().equals("")&&
                !compoundDto.getBelong().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByGradeAndBelong(
                            compoundDto.getGrade(),
                            compoundDto.getBelong()));
        } else if (!compoundDto.getGrade().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByGradeAndHobby(
                            compoundDto.getGrade(),
                            hobby));
        } else if (!compoundDto.getBelong().equals("")&&
                !compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByBelongAndHobby(
                            compoundDto.getBelong(),
                            hobby));
        } else if (!compoundDto.getName().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByName(
                            compoundDto.getName()));
        } else if (!compoundDto.getGrade().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByGrade(
                            compoundDto.getGrade()));
        } else if (!compoundDto.getBelong().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByDept_name(
                            compoundDto.getBelong()));
        } else if (!compoundDto.getHobby().equals("")) {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectInfoByHobby(hobby));
        } else {
            modelAndView.addObject("outputFormList",
                    studentInfoSelectService.selectAllInfo());
        }
        modelAndView.addObject("count",
                infoCountService.countInfoAny(modelAndView.toString()));
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
