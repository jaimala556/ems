package com.example.demo.service;

import com.example.demo.helper.ProjectsHelper;
import com.example.demo.model.Projects;
import com.example.demo.repository.ProjectsRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepo projectsRepo;
    @Autowired
    private EmployeeService employeeService;

    public String addProject(ProjectsHelper projectsHelper) {
        // Todo: verify all the employees and return all of them in an array
        for (ObjectId id : projectsHelper.getEmpId()) {
            if (!employeeService.existById(id)) {
                return "invalid employeeId" + id;
            }
        }
        Projects projects = new Projects();
        projects.setProjectName(projectsHelper.getProjectName());
        projects.setDescription(projectsHelper.getDescription());
        projects.setBudget(projectsHelper.getBudget());
        projects.setClientName(projectsHelper.getClientName());
        projects.setTechStack(projectsHelper.getTechStack());
        projects.setLastUpdate(LocalDateTime.now());
        projects.setActive(projectsHelper.isActive());
        projects.setDurationStart(projectsHelper.getDurationStart());
        projects.setDurationEnd(projectsHelper.getDurationEnd());
        projects.setEmpId(projectsHelper.getEmpId());
        projectsRepo.save(projects);
        return "Project saved successfully....";
    }

    public String updateById(ObjectId id, ProjectsHelper projectsHelper) {
        Projects oldProjects = projectsRepo.findById(id).orElse(null);
        if (oldProjects == null) {
            return "project id is not found...";
        }
        oldProjects.setProjectName(projectsHelper.getProjectName());
        oldProjects.setBudget(projectsHelper.getBudget());
        oldProjects.setClientName(projectsHelper.getClientName());
        oldProjects.setDescription(projectsHelper.getDescription());
        oldProjects.setLastUpdate(LocalDateTime.now());
        oldProjects.setDurationStart(projectsHelper.getDurationStart());
        oldProjects.setDurationEnd(projectsHelper.getDurationEnd());
        oldProjects.setEmpId(projectsHelper.getEmpId());
        projectsRepo.save(oldProjects);
        return "Project has been updated";
    }

    public Optional<Projects> findById(ObjectId id) {
        return projectsRepo.findById(id);
    }

    public boolean existsById(ObjectId id) {
        return projectsRepo.existsById(id);
    }

    public List<Projects> getAllProjects() {
        return projectsRepo.findAll();
    }

    public String deleteById(ObjectId id) {
        if (!projectsRepo.existsById(id)) {
            return "Project id not available.." + id;
        }
        projectsRepo.deleteById(id);
        return "project is deleted by id" + id;
    }
}
