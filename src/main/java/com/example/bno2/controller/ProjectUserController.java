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
    public List<ProjectUser> selectProjectUsers(@RequestParam(name="project_name",required = false) String project_name,@RequestParam(name="user_name",required = false) String user_name, @RequestParam(name="stateCode") String stateCode) {

        return projectUserService.selectProjectUsers(project_name, user_name, stateCode);

    }

    @Operation(summary = "프로젝트 사용자 수정")
    @PostMapping("/updateProjectUser")
    public ResponseEntity<String> updateProjectUser(String project_uid, int user_pn, int new_user_pn) {

        return projectUserService.updateProjectUser(project_uid, user_pn, new_user_pn);

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
    public ResponseEntity<String> deleteProjectUser(@RequestParam("project_uid") String project_uid, @RequestParam("user_name") String user_name) {

        return projectUserService.deleteProjectUser(project_uid, user_name);

    }
}
