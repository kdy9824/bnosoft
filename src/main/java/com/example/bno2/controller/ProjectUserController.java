package com.example.bno2.controller;


import com.example.bno2.dto.ProjectUser;
import com.example.bno2.service.ProjectUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.bno2.service.UserService;

@Tag(name="프로젝트-사용자")
@Controller
public class ProjectUserController {

    @Autowired
    private ProjectUserService projectUserService;

    @Autowired
    private UserService userService;


    @Operation(summary = "프로젝트 사용자 출력")
    @GetMapping("/selectProjectUsers")
    @ResponseBody
    public List<ProjectUser> selectProjectUsers(@RequestParam(name = "projectName", required = false) String projectName, @RequestParam(name = "userName", required = false) String userName, @RequestParam(name = "stateCode") String stateCode) {

        return projectUserService.selectProjectUsers(projectName, userName, stateCode);

    }

    @Operation(summary = "프로젝트 사용자 출력")
    @GetMapping("/userExistsInProject")
    @ResponseBody
    public ResponseEntity<String> userExistsInProject(@RequestParam("projectUid") String projectUid, @RequestParam("userPn") int userPn) {
        return projectUserService.existsInProject(projectUid, userPn);
    }

//    @Operation(summary = "프로젝트 사용자 추가")
//    @PostMapping("/insertProjectTeam")
//    public ResponseEntity<String> insertProjectTeam(@RequestParam(name="projectUid", required = false) String projectUid, @RequestParam(name="userPn", required = false) int userPn, @RequestParam(name="role", required = false) String role, @RequestParam(name="roleDetail", required = false) String roleDetail) {
//
//        return projectUserService.insertProjectTeam(projectUid, userPn, role, roleDetail);
//
//    }

    @Operation(summary = "프로젝트 사용자 추가")
    @PostMapping("/insertProjectTeam")
    public ResponseEntity<String> insertProjectTeam(@RequestParam(name="projectUid", required = false) String projectUid,
                                                    @RequestParam(name="userPn", required = false) int userPn,
                                                    @RequestParam(name="role", required = false) String role,
                                                    @RequestParam(name="roleDetail", required = false) String roleDetail) {

        ResponseEntity<String> existsResponse = projectUserService.existsInProject(projectUid, userPn);
        if ("해당 프로젝트에 이미 투입중입니다".equals(existsResponse.getBody())) {
            return existsResponse;
        }
        ResponseEntity<String> response = projectUserService.insertProjectTeam(projectUid, userPn, role, roleDetail);
        // 프로젝트 사용자가 성공적으로 추가되었을 때만 사용자의 상태를 변경
        if ("프로젝트 팀원 추가 성공".equals(response.getBody())) {
            // 사용자 상태를 변경하는 메서드 호출
            ResponseEntity<String> updateStateResponse = userService.updateUserState("PJT", userPn);

            // 상태 변경이 성공한 경우에만 반환
            return updateStateResponse;
        } else {
            // 프로젝트 사용자 추가가 실패한 경우에는 그대로 반환
            return response;
        }
    }

    @Operation(summary = "프로젝트 사용자 수정")
    @PostMapping("/updateProjectUser")
    public ResponseEntity<String> updateProjectUser(String projectUid, int userPn, String role, String roleDetail) {

        return projectUserService.updateProjectUser(projectUid, userPn, role, roleDetail);

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
    public ResponseEntity<String> deleteProjectUser(@RequestParam("projectUid") String projectUid, @RequestParam("userPn") int userPn) {

        ResponseEntity<String> response = projectUserService.deleteProjectUser(projectUid, userPn);

        // 프로젝트 사용자가 성공적으로 추가되었을 때만 사용자의 상태를 변경
        if ("ProjectUser deleted successfully".equals(response.getBody())) {
            // 사용자 상태를 변경하는 메서드 호출
            ResponseEntity<String> updateStateResponse = userService.updateUserState("STD", userPn);

            // 상태 변경이 성공한 경우에만 반환
            return updateStateResponse;
        } else {
            // 프로젝트 사용자 추가가 실패한 경우에는 그대로 반환
            return response;
        }
    }


}

