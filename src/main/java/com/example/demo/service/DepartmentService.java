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

    public void savedepartment(DepartmentHelper departmentHelper) {
        Departments departments = new Departments();
        departments.setDeptName(departmentHelper.getDeptName());
        departments.setDescription(departmentHelper.getDescription());
        departments.setMaxEmployee(departmentHelper.getMaxEmployee());
        departments.setActive(departmentHelper.isActive());
        departments.setLastUpdate(LocalDateTime.now());
        departmentRepo.save(departments);

    }

    public Optional<Departments> findById(ObjectId id) {
        return departmentRepo.findById(id);
    }

    public String deleteById(ObjectId id) {
        boolean isAvailable = departmentRepo.existsById(id);
        if (!isAvailable) {
            return "Department not found";
        }
        departmentRepo.deleteById(id);
        return "department deleted successfully";
    }

    public String updateById(ObjectId id, DepartmentHelper departmentHelper) {
        Departments oldDepartment = departmentRepo.findById(id).orElse(null);
        if (oldDepartment == null) {
            return "Department not available by this Id : " + id;
        }
        oldDepartment.setDeptName(departmentHelper.getDeptName());
        oldDepartment.setDescription(departmentHelper.getDescription());
        oldDepartment.setMaxEmployee(departmentHelper.getMaxEmployee());
        oldDepartment.setLastUpdate(LocalDateTime.now());
        oldDepartment.setActive(departmentHelper.isActive());
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
        boolean isAvailable = departmentRepo.existsByDeptName(deptName);
        if (!isAvailable) {
            return "Department name is not available .......";
        }
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

}
