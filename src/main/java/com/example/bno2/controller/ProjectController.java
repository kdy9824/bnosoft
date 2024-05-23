package com.example.bno2.controller;

import com.example.bno2.dto.Project;
import com.example.bno2.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="프로젝트")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "프로젝트 출력")
    @GetMapping("/selectProject")
    @ResponseBody
    public List<Project> selectProjects( @RequestParam(name="projectName" ,required = false) String projectName, @RequestParam(name="stateCode") String stateCode) {
        return projectService.selectProjectsByName(projectName, stateCode);


    }

    @Operation(summary = "프로젝트 추가")
    @PostMapping("/insertProject")
    public ResponseEntity<String> insertProject(@RequestBody Project project, HttpSession session) {

        return projectService.insertProject(project, session);

    }

    @Operation(summary = "프로젝트 수정")
    @PostMapping("/updateProject")
    public ResponseEntity<String> updateProject(@RequestBody Project project, HttpSession session) {

        return projectService.updateProject(project, session);

    }

    @Operation(summary = "프로젝트 삭제")
    @PostMapping("/deleteProject")
    public ResponseEntity<String> deleteProject(@RequestParam("projectUid") String projectUid) {

        return projectService.deleteProject(projectUid);

    }

}