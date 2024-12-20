package com.example.demo.service;

import com.example.demo.helper.SalaryHelper;
import com.example.demo.model.Employees;
import com.example.demo.model.Salary;
import com.example.demo.repository.SalaryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class SalaryService {
    @Autowired
    private SalaryRepo salaryRepo;
    @Autowired
    EmployeeService employeeService;

    public String createSalary(SalaryHelper salaryHelper) {
        Employees employees = employeeService.findById(salaryHelper.getEmpId()).orElse(null);
        if (employees == null) {
            return "invalid employee id...";
        }
        double baseSalaryForOneDay = employees.getBaseSalary();
        String department = employees.getDepartmentName();
        Salary salary = new Salary();
        salary.setWorkingDays(salaryHelper.getWorkingDays());
        salary.setTotalWorkingDays(salaryHelper.getWorkingDays());
        salary.setLeave(salaryHelper.getLeave());
        salary.setBaseSalary(baseSalaryForOneDay * salaryHelper.getWorkingDays());
        salary.setMonthYear(salaryHelper.getMonthYear());
        salary.setBonus(salaryHelper.getBonus());
        double calculateOverTime = switch (department) {
            case "admin" -> salaryHelper.getOvertime() * 1000;
            case "manager" -> salaryHelper.getOvertime() * 800;
            case "hr" -> salaryHelper.getOvertime() * 700;
            case "fullStackDeveloper" -> salaryHelper.getOvertime() * 500;
            case "backendDeveloper" -> salaryHelper.getOvertime() * 400;
            default -> 1;
        };
        salary.setOvertime(calculateOverTime);
        salary.setTotalSalary(salary.getBaseSalary()+salary.getBonus()+salary.getOvertime());
        salary.setLastUpdate(LocalDateTime.now());
        salary.setPaid(false);
        salaryRepo.save(salary);
        return "salary created for the month "+salary.getMonthYear();
    }

}
