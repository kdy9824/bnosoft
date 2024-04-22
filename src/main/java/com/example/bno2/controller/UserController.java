package com.example.bno2.controller;

import com.example.bno2.model.User;
import com.example.bno2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="사용자")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @Operation(summary = "사용자 리스트 출력")
//    @GetMapping("/")
//    public String index(Model model) {
//
//        List<User> userList = userService.getAllUsersOrderByPos();
//        model.addAttribute("userList", userList);
//
//        return "user";
//
//    }

    @Operation(summary = "정렬(부서) 사용자 리스트 출력")
    @GetMapping("/sortByDept")
    @ResponseBody
    public List<User> sortByDept() {

        List<User> userList = userService.getAllUsersOrderByDept();

        return userList;

    }

    @Operation(summary = "정렬(이름) 사용자 리스트 출력")
    @GetMapping("/sortByName")
    @ResponseBody
    public List<User> sortByName() {

        List<User> userList = userService.getAllUsersOrderByName();

        return userList;

    }

    @Operation(summary = "정렬(직급) 사용자 리스트 출력")
    @GetMapping("/sortByPos")
    @ResponseBody
    public List<User> sortByPos() {

        List<User> userList = userService.getAllUsersOrderByPos();

        return userList;

    }

    @Operation(summary="입력한 이름을 포함한 사용자 리스트 출력")
    @GetMapping("/sortByName2")
    @ResponseBody
    public List<User> sortByName2(@RequestParam String inputName) {

        List<User> userList = userService.getAllUsersOrderByName2(inputName);

        return userList;

    }

    @Operation(summary="사용자 테이블에 데이터 추가")
    @PostMapping("/insertUser")
    @ResponseBody
    public void insertUser(@RequestParam String email, String name, String dept, String con, String pos, String state) {

        userService.addUser(email, name, dept, con, pos, state);

    }

    @Operation(summary = "사용자 추가")
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        boolean rowsInserted = userService.addUser(user);
        if (rowsInserted == true) {
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "사용자 수정")
    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        int rowsUpdated = userService.updateUser(user);
        if (rowsUpdated > 0) {
            return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}