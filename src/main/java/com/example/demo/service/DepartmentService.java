package com.example.demo.service;

import com.example.demo.helper.DepartmentHelper;
import com.example.demo.model.Departments;
import com.example.demo.repository.DepartmentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EmployeeService employeeService;

    public String savedepartment(DepartmentHelper departmentHelper) {
        // return if already exists
        if (departmentRepo.existsByDeptName(departmentHelper.getDeptName().toLowerCase().trim()))
            return "Department already exists";
        // create new
        Departments departments = new Departments();
        departments.setDeptName(departmentHelper.getDeptName().trim().toLowerCase());
        departments.setDescription(departmentHelper.getDescription().trim().toLowerCase());
//        departments.setMaxEmployee(departmentHelper.getMaxEmployee());
        departments.setActive(departmentHelper.isActive());
        departments.setLastUpdate(LocalDateTime.now());
        departmentRepo.save(departments);
        return "Department created";
    }

    public Optional<Departments> findById(ObjectId id) {
        return departmentRepo.findById(id);
    }

    public String deleteById(ObjectId id) {
        Departments departments = departmentRepo.findById(id).orElse(null);
        if (departments == null) return "Department not found";

        if (employeeService.checkEmployeeForDepartment(departments.getDeptName()))
            return "Employee exists.. department not deleted";

        departmentRepo.deleteById(id);
        return "department deleted successfully";
    }

    public String updateById(ObjectId id, DepartmentHelper departmentHelper) {
        Departments oldDepartment = departmentRepo.findById(id).orElse(null);
        if (oldDepartment == null) {
            return "Department not available by this Id : " + id;
        }
        // checking record is updated, not null, not equal
        if (departmentHelper.getDeptName() != null && !departmentHelper.getDeptName().isEmpty() && !departmentHelper.getDeptName().equals(oldDepartment.getDeptName())) {
            oldDepartment.setDeptName(departmentHelper.getDeptName());
        }
        oldDepartment.setDescription(departmentHelper.getDescription());
        oldDepartment.setMaxEmployee(departmentHelper.getMaxEmployee());
        oldDepartment.setActive(departmentHelper.isActive());

        oldDepartment.setLastUpdate(LocalDateTime.now());
        departmentRepo.save(oldDepartment);
        return "Department Updated Successfully";
    }

    public boolean existsById(ObjectId id) {
        return departmentRepo.existsById(id);

    }

    public List<Departments> findAll() {
        return departmentRepo.findAll();
    }

    /// /////////////////// by name//////////////
    /// find by department name, delete by department name,exists by department name
    public boolean existsByDeptName(String deptName) {
        return departmentRepo.existsByDeptName(deptName);

    }

    public String deleteByDeptName(String deptName) {
        if (!departmentRepo.existsByDeptName(deptName)) return "Department name is not available .......";
        if (employeeService.checkEmployeeForDepartment(deptName)) return "Employee exists.. department not deleted";
        departmentRepo.deleteByDeptName(deptName);
        return "department is deleted successfully.......";
    }

    public Optional<Departments> findByDeptName(String deptName) {
        return departmentRepo.findByDeptName(deptName);
    }

    public String updateByDeptName(String deptName, DepartmentHelper departmentHelper) {
        Departments oldDepartment = departmentRepo.findByDeptName(deptName).orElse(null);
        if (oldDepartment == null) {
            return "department is not found............";

        }
        oldDepartment.setDeptName(departmentHelper.getDeptName());
        oldDepartment.setDescription(departmentHelper.getDescription());
        oldDepartment.setMaxEmployee(departmentHelper.getMaxEmployee());
        oldDepartment.setLastUpdate(LocalDateTime.now());
        oldDepartment.setActive(departmentHelper.isActive());
        departmentRepo.save(oldDepartment);
        return "department is updated";
    }

    // accept department name and return their no. of employees
    public int numberOfEmployeeDept(String department) {
        Departments departments = departmentRepo.findByDeptName(department).orElse(null);
        if (departments == null)
            return 0;
        return departments.getMaxEmployee();
    }
}
