package com.example.bno2.controller;

import com.example.bno2.dto.Project;
import com.example.bno2.dto.User;
import com.example.bno2.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
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

    @Operation(summary = "프로젝트 출력")
    @GetMapping("/selectProject")
    @ResponseBody
    public List<Project> selectProjects( @RequestParam(name="projectName" ,required = false) String projectName,@RequestParam(name="stateCode") String stateCode) {

        List<Project> projectList;

        if("ALL".equals(stateCode)){
            stateCode =null;
        }
        projectList = projectService.selectProjectsByName(projectName, stateCode);


        // 코드화된 데이터를 텍스트로 대체
        for (Project project : projectList) {
            project.setStateCode(replaceStateText(project.getStateCode()));
        }

        return projectList;

    }

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {
        if ("COM".equals(state)) {
            return "완료";
        } else if ("ING".equals(state)) {
            return "진행중";
        } else if ("WAT".equals(state)) {
            return "대기중";
        } else {
            return state;
        }
    }

    @Operation(summary = "프로젝트 추가")
    @PostMapping("/insertProject")
    public ResponseEntity<String> insertProject(@RequestBody Project project, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        int loginUserPn = loginUser.getPn();

        int rowsInserted = projectService.insertProject(project, loginUserPn);

        if (rowsInserted > 0) {
            return new ResponseEntity<>("Project inserted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to insert project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "프로젝트 수정")
    @PostMapping("/updateProject")
    public ResponseEntity<String> updateProject(@RequestBody Project project, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        int loginUserPn = loginUser.getPn();

        int rowsUpdated = projectService.updateProject(project, loginUserPn);

        if (rowsUpdated > 0) {
            return new ResponseEntity<>("Project updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}