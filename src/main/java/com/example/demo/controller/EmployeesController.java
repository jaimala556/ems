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
 private   EmployeeService employeeService;
    @PostMapping("/employees")
    public void saveEmployee(@RequestBody EmployeesHelper employeesHelper){
        employeeService.saveEmployee(employeesHelper);
    }
  @GetMapping("/employees-id/{id}")
    public Optional<Employees> findById(@PathVariable ObjectId id) {
        return employeeService.findById(id);
    }

    @PutMapping("/employees-id/{id}")
    public String updateEmployee(@PathVariable ObjectId id,@RequestBody EmployeesHelper employeesHelper){
      return  employeeService.updateEmployee(id,employeesHelper);
    }
  @DeleteMapping("/employees-id/{id}")
    public String deleteById(@PathVariable ObjectId id){
        return employeeService.deleteById(id);
    }

    /// /////////////by email/////////////
    @PutMapping("/employees-email/{email}")
    public String updateEmployee(@PathVariable String empEmail,@RequestBody EmployeesHelper employeesHelper){
        return employeeService.updateEmployee(empEmail,employeesHelper);
    }
    @DeleteMapping("/employees-email/{email}")
    public String deleteByEmail(@PathVariable String empEmail){
        return employeeService.deleteByEmail(empEmail);
    }
    @GetMapping("/employees-email/{email}")
    public Optional<Employees> findByEmail(@PathVariable String empEmail){
        return employeeService.findByEmail(empEmail);
    }
}
