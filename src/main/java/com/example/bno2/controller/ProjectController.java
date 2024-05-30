package com.example.bno2.controller;

import com.example.bno2.dto.Project;
import com.example.bno2.dto.User;
import com.example.bno2.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.bno2.service.UserService;

import java.util.List;

@Tag(name="프로젝트")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "프로젝트 출력")
    @GetMapping("/selectProject")
    @ResponseBody
    public List<Project> selectProjects( @RequestParam(name="projectName" ,required = false) String projectName, @RequestParam(name="projectStateCode") String projectStateCode) {
        return projectService.selectProjectsByName(projectName, projectStateCode);


    }

    @Operation(summary = "프로젝트 추가")
    @PostMapping("/insertProject")
    public ResponseEntity<String> insertProject(@RequestBody Project project, HttpSession session) {

        return projectService.insertProject(project, session);

    }

    @Operation(summary = "프로젝트 수정")
    @PostMapping("/updateProject")
    public ResponseEntity<String> updateProject(@RequestBody Project project, HttpSession session) {

        ResponseEntity<String> response = projectService.updateProject(project, session);
        if ("프로젝트 수정 완료".equals(response.getBody()) && "COM".equals(project.getProjectStateCode())) {
            // 프로젝트 팀원이 성공적으로 추가되었고 프로젝트 상태가 'COM'인 경우에만 사용자의 상태를 변경
            ResponseEntity<String> updateStateResponse = projectService.updateUserStateForCompletedProject(project.getProjectUid());


                return updateStateResponse;

        } else {
            // 프로젝트 팀원 추가가 실패했거나 프로젝트 상태가 'COM'이 아닌 경우에는 그대로 반환
            return response;
        }
    }

    @Operation(summary = "프로젝트 삭제")
    @PostMapping("/deleteProject")
    public ResponseEntity<String> deleteProject(@RequestParam("projectUid") String projectUid) {

        ResponseEntity<String> response =  projectService.deleteProject(projectUid);

        if ("project deleted successfully".equals(response.getBody())) {
            // 프로젝트 팀원이 성공적으로 추가되었고 프로젝트 상태가 'COM'인 경우에만 사용자의 상태를 변경
            ResponseEntity<String> updateStateResponse = projectService.updateUserStateForCompletedProject(projectUid);


            return updateStateResponse;

        } else {
            // 프로젝트 팀원 추가가 실패했거나 프로젝트 상태가 'COM'이 아닌 경우에는 그대로 반환
            return response;
        }

    }

}