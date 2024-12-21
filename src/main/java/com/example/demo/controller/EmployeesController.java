package com.example.demo.controller;

import com.example.demo.helper.EmployeesHelper;
import com.example.demo.model.Employees;
import com.example.demo.service.EmployeeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public void saveEmployee(@RequestBody EmployeesHelper employeesHelper) {
        employeeService.saveEmployee(employeesHelper);
    }

    @GetMapping("/employees-id/{id}")
    public Optional<Employees> findById(@PathVariable ObjectId id) {
        return employeeService.findById(id);
    }

    @PutMapping("/employees-id/{id}")
    public String updateById(@PathVariable ObjectId id, @RequestBody EmployeesHelper employeesHelper) {
        return employeeService.updateById(id, employeesHelper);
    }

    @DeleteMapping("/employees-id/{id}")
    public String deleteById(@PathVariable ObjectId id) {
        return employeeService.deleteById(id);
    }

    /// /////////////by email/////////////
    @PutMapping("/employees-email/{email}")
    public String updateByEmail(@PathVariable String email, @RequestBody EmployeesHelper employeesHelper) {
        return employeeService.updateByEmail(email, employeesHelper);
    }

    @DeleteMapping("/employees-email/{email}")
    public String deleteByEmail(@PathVariable String email) {
        return employeeService.deleteByEmail(email);
    }

    @GetMapping("/employees-email/{email}")
    public Optional<Employees> findByEmail(@PathVariable String email) {
        return employeeService.findByEmail(email);
    }
}
