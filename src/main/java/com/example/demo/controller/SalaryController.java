package com.example.demo.controller;

import com.example.demo.helper.SalaryHelper;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {
    @Autowired private SalaryService salaryService;
    @PostMapping("/salary")
    public String createSalary(@RequestBody SalaryHelper salaryHelper){
//        return salaryService.createSalary(salaryHelper);
        return "hello world";
    }
}
