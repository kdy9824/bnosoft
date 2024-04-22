package com.example.bno2.controller;

import com.example.bno2.model.Equipment;
import com.example.bno2.model.Project;
import com.example.bno2.model.User;
import com.example.bno2.service.EquipmentService;
import com.example.bno2.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tag(name="프로젝트")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "프로젝트 출력")
    @GetMapping("/selectProject")
    @ResponseBody
    public List<Project> selectAllProjects() {

        List<Project> projectList = projectService.selectAllProjects();

        return projectList;

    }

    @Operation(summary = "프로젝트 추가")
    @PostMapping("/insertProject")
    public ResponseEntity<String> insertProject(@RequestBody Project project) {
        int rowsInserted = projectService.insertProject(project);
        if (rowsInserted > 0) {
            return new ResponseEntity<>("Project inserted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to insert project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "프로젝트 수정")
    @PostMapping("/updateProject")
    public ResponseEntity<String> updateProject(@RequestBody Project project) {
        int rowsUpdated = projectService.updateProject(project);
        if (rowsUpdated > 0) {
            return new ResponseEntity<>("Project updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}