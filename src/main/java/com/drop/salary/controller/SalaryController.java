package com.drop.salary.controller;

import com.drop.salary.pojo.SalaryModel;
import com.drop.salary.util.CalSalaryUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * @Description: 薪资计算器
 * @Author: Leo
 * @Date: 2018-05-08 下午 3:02
 */
@RestController
public class SalaryController {

    @GetMapping("/")
    public ModelAndView goView(){
        return new ModelAndView("/cal");
    }

    @PostMapping("/cal")
    public String calSalary(SalaryModel model){
        BigDecimal b = CalSalaryUtil.calSalary(model);
        return b.toString();
    }
}
