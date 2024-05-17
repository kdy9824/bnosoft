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

    @Operation(summary = "회원가입")
    @PostMapping("/memberJoin")
    @ResponseBody
    public ResponseEntity<String> memberJoin(HttpSession session, @RequestBody User user) {

        if(joinService.addUser(user) > 0){
            session.removeAttribute("createdAuthCode");
            return new ResponseEntity<>("BNOSOFT에 가입하신 것을 환영합니다.", HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("회원가입에 실패하였습니다.", HttpStatus.OK);

    }

    @Operation(summary = "이메일 중복 검사")
    @GetMapping("/checkEmailDuplicate")
    @ResponseBody
    public ResponseEntity<String> checkEmailDuplicate(@RequestParam String inputEmail){

        if(joinService.checkEmailDuplicate(inputEmail) == 0){
            return new ResponseEntity<>("사용 가능한 이메일입니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("중복된 이메일은 사용할 수 없습니다.", HttpStatus.OK);
        }

    }

}