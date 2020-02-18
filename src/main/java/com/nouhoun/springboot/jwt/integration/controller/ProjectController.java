package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.exception.ProjectNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1.1")
public class ProjectController {

    @ExceptionHandler({ProjectNotFoundException.class})
    @RequestMapping(value ="/projects")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Map<String, String> getProjectById(@RequestParam String projectId){
        Map<String, String> projects = new HashMap<>();
        projects.put("projectId", "100");
        if(!projects.containsValue(projectId)) {
            throw new ProjectNotFoundException("Resource Not Found");

        }

        return projects;
    }
}
