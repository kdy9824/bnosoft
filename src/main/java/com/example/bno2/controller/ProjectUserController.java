package com.example.bno2.controller;


import com.example.bno2.dto.EUP;
import com.example.bno2.dto.ProjectUser;
import com.example.bno2.service.ProjectService;
import com.example.bno2.service.ProjectUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="프로젝트-사용자")
@Controller
public class ProjectUserController {
    @Autowired
    private ProjectUserService projectuserService;

    @Operation(summary = "프로젝트-사용자 출력")
    @GetMapping("/selectProjectUsers")
    @ResponseBody
    public List<ProjectUser> selectProjectUsers(@RequestParam(name="project_name",required = false) String project_name,@RequestParam(name="user_name",required = false) String user_name, @RequestParam(name="stateCode") String stateCode) {

        List<ProjectUser> projectuserList;


        if("ALL".equals(stateCode)){
            stateCode =null;
        }
        projectuserList = projectuserService.selectProjectUsers(project_name,user_name, stateCode);

        // 코드화된 데이터를 텍스트로 대체
        for (ProjectUser projectuser : projectuserList) {
            projectuser.setStateCode(replaceStateText(projectuser.getStateCode()));
        }

        return projectuserList;

    }

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {
        if ("COM".equals(state)) {
            return "완료";
        } else if ("ING".equals(state)) {
            return "진행 중";
        } else if ("WAT".equals(state)) {
            return "완료";
        } else {
            return state;
        }
    }

    @Operation(summary = "프로젝트 사용자 수정")
    @PostMapping("/updateProjectUser")
    public ResponseEntity<String> updateProjectUser(@RequestBody ProjectUser projectuser) {
        System.out.println("equipmentUser : " +projectuser);
        int rowsUpdated = projectuserService.updateProjectUser(projectuser.getProject_uid(),projectuser.getUser_pn(),projectuser.getNew_user_pn());
        if (rowsUpdated > 0) {
            return new ResponseEntity<>("ProjectUser updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update ProjectUser", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "프로젝트 사용자 리스트 데이터 출력")
    @GetMapping("/selectProjectUsersData")
    @ResponseBody
    public List<ProjectUser> selectProjectUsersData() {
        return projectuserService.selectProjectUsersData();

    }

    @Operation(summary = "프로젝트 사용자 삭제")
    @PostMapping("/deleteProjectUser")
    public ResponseEntity<String> deleteProjectUser(@RequestParam("project_uid") String project_uid, @RequestParam("user_name") String user_name) {
        int rowsDeleted = projectuserService.deleteProjectUser(project_uid, user_name);
        if (rowsDeleted > 0) {
            return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to delete equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @Operation(summary = "프로젝트 사용자 삭제")
//    @DeleteMapping("/deleteProjectUser")
//    public ResponseEntity<String> deleteProjectUser(@RequestBody ProjectUser projectUser) {
//        int rowsDeleted = projectuserService.deleteProjectUser(projectUser.getProject_uid(), projectUser.getUser_pn());
//        if (rowsDeleted > 0) {
//            return new ResponseEntity<>("Project user deleted successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to delete project user", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
