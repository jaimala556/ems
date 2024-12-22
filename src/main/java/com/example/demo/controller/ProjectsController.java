package com.example.demo.controller;

import com.example.demo.helper.ProjectsHelper;
import com.example.demo.model.Projects;
import com.example.demo.service.ProjectsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @PostMapping("/project")
    public String addProject(@RequestBody ProjectsHelper projectsHelper) {
        return projectsService.addProject(projectsHelper);
    }

    @PutMapping("/project/{id}")
    public String updateById(@PathVariable ObjectId id, @RequestBody ProjectsHelper projectsHelper) {
        return projectsService.updateById(id, projectsHelper);
    }

    @GetMapping("/project/{id}")
    public Optional<Projects> findById(@PathVariable ObjectId id) {
        return projectsService.findById(id);
    }

    @GetMapping("/projects")
    public List<Projects> getAllProjects() {
        return projectsService.getAllProjects();
    }

    @DeleteMapping("/project/{id}")
    public String deleteById(@PathVariable ObjectId id) {
        return projectsService.deleteById(id);
    }
}