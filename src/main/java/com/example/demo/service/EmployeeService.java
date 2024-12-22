package com.example.demo.service;

import com.example.demo.helper.EmployeesHelper;
import com.example.demo.model.Employees;
import com.example.demo.repository.EmployeeRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UsersService usersService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProjectsService projectsService;

    ///////////////// by id /////////////////////////////
    public String saveEmployee(EmployeesHelper employeesHelper) {
        // check user email is already exists or not
        if (employeeRepo.existsByEmail(employeesHelper.getEmail()))
            return "Employee already registered with this email address";

        // check department is valid or not
        if (departmentService.existsByDeptName(employeesHelper.getDepartmentName().trim().toLowerCase()))
            return "Invalid department";

        // how many employees are register in specific department
        // validate department members
        // admin-> 10
        // hr-> 15
        // manager-> 5
        // it-> 20
        // customer care-> 25
        List<Employees> allEmployees = employeeRepo.findAll();
        int maxEmpInDepartment = departmentService.numberOfEmployeeDept(employeesHelper.getDepartmentName().trim().toLowerCase());
        int totalEmp = 0;

        // find no. of employees
        for (Employees employees : allEmployees)
            if (employees.getDepartmentName().equals(employeesHelper.getDepartmentName())) totalEmp++;
        if (totalEmp == maxEmpInDepartment) return "Employees limit reached";

        // save user
        Employees employees = new Employees();
        employees.setFirstName(employeesHelper.getFirstName().trim().toLowerCase());
        employees.setLastName(employeesHelper.getLastName().trim().toLowerCase());
        employees.setActive(employeesHelper.isActive());
        employees.setEmail(employeesHelper.getEmail().trim().toLowerCase());
        employees.setHireBy(employeesHelper.getHireBy());
        employees.setBaseSalary(employeesHelper.getBaseSalary());
        employees.setDepartmentName(employeesHelper.getDepartmentName().trim().toLowerCase());
        employees.setJoiningDate(employeesHelper.getJoiningDate());

        // verify project ids if available in helper
        List<ObjectId> invalidProjectIds = new ArrayList<>();// invalid project ids
        if (!employeesHelper.getProjectIds().isEmpty()) {
            List<ObjectId> ids = new ArrayList<>();// valid project ids

            for (ObjectId id : employeesHelper.getProjectIds()) {
                if (projectsService.existsById(id)) ids.add(id);
                else invalidProjectIds.add(id);
            }
            employees.setProjectIds(ids);
        }
        employees.setLastUpdate(LocalDateTime.now());
        Employees newEmployee = employeeRepo.save(employees);
        usersService.addUsers(employees.getFirstName(), employees.getEmail(), newEmployee.getId());
        return "Employee saved";// invalid project id are return
    }

    public Optional<Employees> findById(ObjectId id) {
        return employeeRepo.findById(id);
    }

    public String updateById(ObjectId id, EmployeesHelper employeesHelper) {
        Employees oldEmployee = employeeRepo.findById(id).orElse(null);
        if (oldEmployee == null) {
            return "Record is not found by this id";
        }
        oldEmployee.setFirstName(employeesHelper.getFirstName());
        oldEmployee.setLastName(employeesHelper.getLastName());
        oldEmployee.setActive(employeesHelper.isActive());
        oldEmployee.setEmail(employeesHelper.getEmail().trim().toLowerCase()); // change the email in users
        oldEmployee.setHireBy(employeesHelper.getHireBy());
        oldEmployee.setBaseSalary(employeesHelper.getBaseSalary());
        oldEmployee.setDepartmentName(employeesHelper.getDepartmentName().trim().toLowerCase()); //
        oldEmployee.setJoiningDate(employeesHelper.getJoiningDate());
        oldEmployee.setProjectIds(employeesHelper.getProjectIds());
        oldEmployee.setLastUpdate(LocalDateTime.now());
        employeeRepo.save(oldEmployee);
        return "Employee is updated successfully";
    }

    public String deleteById(ObjectId id) {
        boolean isAvailable = employeeRepo.existsById(id);
        if (!isAvailable) {
            return "employee is not found by this id : " + id;
        }
        employeeRepo.deleteById(id);
        // TODO: remove data from users also
        return "employee deleted by this id : " + id;
    }

    public boolean existById(ObjectId id) {
        return employeeRepo.existsById(id);

    }

    //////////////////////// by email ////////////////
    public Optional<Employees> findByEmail(String empEmail) {
        return employeeRepo.findByEmail(empEmail);
    }

    public String deleteByEmail(String empEmail) {
        boolean isAvailable = employeeRepo.existsByEmail(empEmail);
        if (!isAvailable) {
            return "employee is not available on this email...... ";
        }
        employeeRepo.deleteByEmail(empEmail);
        // TODO: remove data from users also
        return "employee is deleted through this email : " + empEmail;
    }

    public boolean existsByEmail(String empEmail) {
        return employeeRepo.existsByEmail(empEmail);
    }

    public String updateByEmail(String empEmail, EmployeesHelper employeesHelper) {
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

    public boolean checkEmployeeForDepartment(String department) {
        List<Employees> allEmployees = employeeRepo.findAll();
        for (Employees employees : allEmployees) {
            if (employees.getDepartmentName().equals(department)) return true;
        }
        return false;
    }
}
