package com.example.bno2.controller;

import com.example.bno2.dto.User;
import com.example.bno2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="사용자")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private void replaceCodeToText(User user){
        user.setDeptCode(replaceDeptText(user.getDeptCode()));
        user.setPosCode(replacePosText(user.getPosCode()));
        user.setStateCode(replaceStateText(user.getStateCode()));
    }

    // dept 값에 따라 텍스트를 대체하는 메서드
    private String replaceDeptText(String dept) {
        if ("SER".equals(dept)) {
            return "서비스사업본부";
        } else if ("FIN".equals(dept)) {
            return "금융사업본부";
        } else if ("ALM".equals(dept)) {
            return "ALM사업본부";
        } else if ("MAN".equals(dept)) {
            return "경영본부";
        } else {
            return dept;
        }
    }

    // pos 값에 따라 텍스트를 대체하는 메서드
    private String replacePosText(String pos) {
        if ("S01".equals(pos)) {
            return "사장";
        } else if ("J01".equals(pos)) {
            return "전무";
        } else if ("E01".equals(pos)) {
            return "이사";
        } else if ("B01".equals(pos)) {
            return "부장";
        } else if ("C01".equals(pos)) {
            return "차장";
        } else if ("K01".equals(pos)) {
            return "과장";
        } else if ("D01".equals(pos)) {
            return "대리";
        } else if ("S02".equals(pos)) {
            return "사원";
        } else {
            return pos;
        }
    }

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {
        if ("STD".equals(state)) {
            return "본사 대기";
        } else if ("PJT".equals(state)) {
            return "프로젝트 투입";
        } else if ("OUT".equals(state)) {
            return "퇴사";
        } else {
            return state;
        }
    }

    @Operation(summary="이름으로 사용자 조회")
    @GetMapping("/selectUsersByName")
    @ResponseBody
    public List<User> selectUsersByName(@RequestParam(required = false) String name, @RequestParam String dept) {

        List<User> userList;

        if(dept.equals("ALL")){
            userList = userService.selectUsersByName(name);
        } else {
            userList = userService.selectUsersByNameDept(name, dept);
        }

        // 코드화된 데이터를 텍스트로 대체
        for (User user : userList) {
            replaceCodeToText(user);
        }

        return userList;

    }

    @Operation(summary="사용자 테이블에 데이터 추가")
    @PostMapping("/insertUser")
    @ResponseBody
    public ResponseEntity<String> insertUser(@RequestBody User user, HttpSession session) {

//        user.setEmail(user.getEmail().concat("@bnosoft.co.kr"));

        User loginUser = (User)session.getAttribute("loginUser");

        int loginUserPn = loginUser.getPn();

        int rowsInserted = userService.addUser(user, loginUserPn);

        if (rowsInserted > 0) {
            return new ResponseEntity<>("User inserted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to insert user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "사용자 수정")
    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user, HttpSession session) {

//        user.setEmail(user.getEmail().concat("@bnosoft.co.kr"));

        User loginUser = (User)session.getAttribute("loginUser");

        int loginUserPn = loginUser.getPn();

        int rowsUpdated = userService.updateUser(user, loginUserPn);

        if (rowsUpdated > 0) {
            return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}