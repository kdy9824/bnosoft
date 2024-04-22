package com.example.bno2.controller;

import com.example.bno2.model.User;
import com.example.bno2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {

        List<User> userList = userService.getAllUsersOrderByPos();
        model.addAttribute("userList", userList);

        return "user";

    }

    @PostMapping("/sortByDept")
    public ResponseEntity<List<User>> sortByDept() {

        List<User> userList = userService.getAllUsersOrderByDept();

        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

    @PostMapping("/sortByName")
    public ResponseEntity<List<User>> sortByName() {

        List<User> userList = userService.getAllUsersOrderByName();

        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

    @PostMapping("/sortByPos")
    public ResponseEntity<List<User>> sortByPos() {

        List<User> userList = userService.getAllUsersOrderByPos();

        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

    @PostMapping("/sortByName2")
    public ResponseEntity<List<User>> sortByName2(@RequestParam String inputName) {

        List<User> userList = userService.getAllUsersOrderByName2(inputName);

        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

}