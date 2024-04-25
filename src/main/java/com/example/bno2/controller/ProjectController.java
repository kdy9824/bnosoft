package com.example.bno2.controller;

import com.example.bno2.model.Project;
import com.example.bno2.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="프로젝트")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "프로젝트 조회")
    @GetMapping("/selectProjectsByNameProject")
    @ResponseBody
    public List<Project> selectProjects(@RequestParam(required = false) String name, @RequestParam(required = false) String project, @RequestParam String pjtState) {

        List<Project> projectList;

        if(pjtState.equals("ALL")){
            projectList = projectService.selectProjectsByNameProject(name,project);
        } else {
            projectList = projectService.selectProjectsByNameProjectState(name,project,pjtState);
        }

        // 코드화된 데이터를 텍스트로 대체
        for (Project p : projectList) {
            replaceCodeToText(p);
        }

        return projectList;

    }

    private void replaceCodeToText(Project project){
        project.setPjtState(replaceStateText(project.getPjtState()));
    }

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {
        if ("CON".equals(state)) {
            return "진행 중";
        } else if ("COM".equals(state)) {
            return "완료";
        } else {
            return state;
        }
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