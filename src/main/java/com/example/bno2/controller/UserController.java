package com.example.bno2.controller;

import com.example.bno2.dto.User;
import com.example.bno2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="사용자")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary="이름으로 사용자 조회")
    @GetMapping("/selectUsersByName")
    @ResponseBody
    public List<User> selectUsersByName(@RequestParam(required = false) String name, @RequestParam String dept) {
        return userService.selectUsersByName(name, dept);

    }

    @Operation(summary="사용자 테이블에 데이터 추가")
    @PostMapping("/insertUser")
    @ResponseBody
    public ResponseEntity<String> insertUser(@RequestBody User user, HttpSession session) {

        return userService.addUser(user, session);

    }

    @Operation(summary = "사용자 수정")
    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user, HttpSession session) {

        return userService.updateUser(user, session);

    }

    @Operation(summary = "사용자 상태 변경")
    @PostMapping("/updateUserState")
    public ResponseEntity<String> updateUserState(@RequestParam("stateCode") String stateCode,@RequestParam("pn") int pn) {

        return userService.updateUserState(stateCode, pn);

    }

}