package com.example.demo.controller;

import com.example.demo.helper.DepartmentHelper;
import com.example.demo.model.Departments;
import com.example.demo.service.DepartmentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public String saveDepartment(@RequestBody DepartmentHelper departmentHelper) {
        return departmentService.savedepartment(departmentHelper);
    }

    @GetMapping("/department/{id}")
    public Optional<Departments> findById(@PathVariable ObjectId id) {
        return departmentService.findById(id);
    }

    @DeleteMapping("/department/{id}")
    public String deleteById(@PathVariable ObjectId id) {
        return departmentService.deleteById(id);
    }

    @PutMapping("/department/{id}")
    public String updateById(@PathVariable ObjectId id, @RequestBody DepartmentHelper departmentHelper) {
        return departmentService.updateById(id, departmentHelper);
    }

    @GetMapping("/departments")
    public List<Departments> findAll() {
        return departmentService.findAll();
    }

    /// /////////////////////// by name////
    @GetMapping("/department-name/{deptName}")
    public Optional<Departments> findByDeptName(@PathVariable String deptName) {
        return departmentService.findByDeptName(deptName);
    }

    @DeleteMapping("/department-name/{deptName}")
    public String deleteByDeptName(@PathVariable String deptName) {
        return departmentService.deleteByDeptName(deptName);
    }

    @PutMapping("/department-name/{deptName}")
    public String updateByDeptName(@PathVariable String deptName, @RequestBody DepartmentHelper departmentHelper) {
        return departmentService.updateByDeptName(deptName, departmentHelper);
    }
}
