package com.example.bno2.controller;

import com.example.bno2.dto.User;
import com.example.bno2.service.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "회원가입")
    @PostMapping("/memberJoin")
    @ResponseBody
    public ResponseEntity<String> memberJoin(@RequestBody User user) {

        if(joinService.addUser(user) > 0)
            return new ResponseEntity<>("Welcome to bnosoft.", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failde to add user.", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Operation(summary = "이메일 중복 검사")
    @GetMapping("/checkEmailDuplicate")
    @ResponseBody
    public ResponseEntity<String> checkEmailDuplicate(@RequestParam String inputEmail){

        if(joinService.checkEmailDuplicate(inputEmail) == 0){
            return new ResponseEntity<>("Email is unique.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email already exist.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}