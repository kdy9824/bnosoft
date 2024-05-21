package com.example.bno2.controller;


import com.example.bno2.dto.ProjectUser;
import com.example.bno2.service.ProjectUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name="프로젝트-사용자")
@Controller
public class ProjectUserController {

    @Autowired
    private ProjectUserService projectUserService;

    @Operation(summary = "프로젝트 사용자 출력")
    @GetMapping("/selectProjectUsers")
    @ResponseBody
    public List<ProjectUser> selectProjectUsers(@RequestParam(name="projectName", required = false) String projectName, @RequestParam(name="userName", required = false) String userName, @RequestParam(name="stateCode") String stateCode) {

        return projectUserService.selectProjectUsers(projectName, userName, stateCode);

    }

    @Operation(summary = "프로젝트 사용자 수정")
    @PostMapping("/updateProjectUser")
    public ResponseEntity<String> updateProjectUser(String projectUid, int userPn, int newUserPn) {

        return projectUserService.updateProjectUser(projectUid, userPn, newUserPn);

    }

//    @Operation(summary = "프로젝트 사용자 리스트 데이터 출력")
//    @GetMapping("/selectProjectUsersData")
//    @ResponseBody
//    public List<ProjectUser> selectProjectUsersData() {
//
//        return projectUserService.selectProjectUsersData();
//
//    }

    @Operation(summary = "프로젝트 사용자 삭제")
    @PostMapping("/deleteProjectUser")
    public ResponseEntity<String> deleteProjectUser(@RequestParam("projectUid") String projectUid, @RequestParam("userName") String userName) {

        return projectUserService.deleteProjectUser(projectUid, userName);

    }
}
