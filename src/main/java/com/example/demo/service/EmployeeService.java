package com.example.demo.service;

import com.example.demo.helper.EmployeesHelper;
import com.example.demo.model.Employees;
import com.example.demo.repository.EmployeeRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UsersService usersService;

    /// ////////////// by id /////////////////////////////
    public void saveEmployee(EmployeesHelper employeesHelper) {
        Employees employees = new Employees();
        employees.setFirstName(employeesHelper.getFirstName().trim().toLowerCase());
        employees.setLastName(employeesHelper.getLastName().trim().toLowerCase());
        employees.setActive(employeesHelper.isActive());
        employees.setEmail(employeesHelper.getEmail().trim().toLowerCase());
        employees.setLastUpdate(LocalDateTime.now());
        employees.setHireBy(employeesHelper.getHireBy());
        employees.setBaseSalary(employeesHelper.getBaseSalary());
        employees.setDepartmentName(employeesHelper.getDepartmentName().trim().toLowerCase());
        employees.setJoiningDate(employeesHelper.getJoiningDate());
        employees.setProjectIds(employeesHelper.getProjectIds());
        Employees newEmployee = employeeRepo.save(employees);
        usersService.addUsers(employees.getFirstName(), employees.getEmail(), newEmployee.getId());
    }

    public Optional<Employees> findById(ObjectId id) {
        return employeeRepo.findById(id);
    }

    public String updateEmployee(ObjectId id, EmployeesHelper employeesHelper) {
        Employees oldEmployee = employeeRepo.findById(id).orElse(null);
        if (oldEmployee == null) {
            return "Record is not found by this id";
        }
        oldEmployee.setFirstName(employeesHelper.getFirstName());
        oldEmployee.setLastName(employeesHelper.getLastName());
        oldEmployee.setActive(employeesHelper.isActive());
        oldEmployee.setEmail(employeesHelper.getEmail().trim().toLowerCase());
        oldEmployee.setLastUpdate(LocalDateTime.now());
        oldEmployee.setHireBy(employeesHelper.getHireBy());
        oldEmployee.setBaseSalary(employeesHelper.getBaseSalary());
        oldEmployee.setDepartmentName(employeesHelper.getDepartmentName().trim().toLowerCase());
        oldEmployee.setJoiningDate(employeesHelper.getJoiningDate());
        oldEmployee.setProjectIds(employeesHelper.getProjectIds());
        employeeRepo.save(oldEmployee);
        return "Employee is updated successfullly";

    }

    public String deleteById(ObjectId id) {
        boolean isAvailable = employeeRepo.existsById(id);
        if (!isAvailable) {
            return "employee is not found by this id : " + id;
        }
        employeeRepo.deleteById(id);
        return "employee deleted by this id : " + id;
    }

    public boolean existById(ObjectId id) {
        return employeeRepo.existsById(id);

    }

    /// ///////////////////// by email////////////////
    public Optional<Employees> findByEmail(String empEmail) {
        return employeeRepo.findByEmail(empEmail);

    }

    public String deleteByEmail(String empEmail) {
        boolean isAvailable = employeeRepo.existsByEmail(empEmail);
        if (!isAvailable) {
            return "employee is not available on this email...... ";
        }
        employeeRepo.deleteByEmail(empEmail);
        return "employee is deleted through this email : " + empEmail;
    }

    public boolean existsByEmail(String empEmail) {
        return employeeRepo.existsByEmail(empEmail);
    }

    public String updateEmployee(String empEmail, EmployeesHelper employeesHelper) {
        Employees oldEmployee = employeeRepo.findByEmail(empEmail).orElse(null);
        if (oldEmployee == null) {
            return "Employee is not found....";
        }
        oldEmployee.setFirstName(employeesHelper.getFirstName());
        oldEmployee.setLastName(employeesHelper.getLastName());
        oldEmployee.setActive(employeesHelper.isActive());
        oldEmployee.setEmail(employeesHelper.getEmail().trim().toLowerCase());
        oldEmployee.setLastUpdate(LocalDateTime.now());
        oldEmployee.setHireBy(employeesHelper.getHireBy());
        oldEmployee.setBaseSalary(employeesHelper.getBaseSalary());
        oldEmployee.setDepartmentName(employeesHelper.getDepartmentName().trim().toLowerCase());
        oldEmployee.setJoiningDate(employeesHelper.getJoiningDate());
        oldEmployee.setProjectIds(employeesHelper.getProjectIds());
        employeeRepo.save(oldEmployee);
        return "employee is updated....";
    }
}
