package com.example.bno2.controller;

import com.example.bno2.dto.User;
import com.example.bno2.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원가입")
@Controller
public class JoinController {

    @Autowired
    private JoinService joinService;

    @Operation(summary = "이메일 중복 검사")
    @GetMapping("/checkEmailDuplicate")
    @ResponseBody
    public ResponseEntity<String> checkEmailDuplicate(@RequestParam String inputEmail){

        return new ResponseEntity<>(joinService.checkEmailDuplicate(inputEmail), HttpStatus.OK);

    }

    @Operation(summary = "회원가입")
    @PostMapping("/memberJoin")
    @ResponseBody
    public ResponseEntity<String> memberJoin(HttpSession session, @RequestBody User user) {

        return new ResponseEntity<>(joinService.addUser(session,user),HttpStatus.OK);

    }

}