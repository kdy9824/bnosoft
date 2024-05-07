package com.example.bno2.controller;

import com.example.bno2.dao.User;
import com.example.bno2.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "로그인")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "로그인")
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(HttpSession session, @RequestParam String email, @RequestParam String password){

        email = email.concat("@bnosoft.co.kr");

        User loginUser = loginService.login(email, password);
        int loginCount = loginService.loginCount(email, password);

        if(loginCount == 1){
            session.setAttribute("loginUser", loginUser);
//            session.setAttribute("loginUserPn", loginUser.getPn());
            return new ResponseEntity<>("Login success", HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>("Login failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "로그인 유저 확인")
    @GetMapping("/checkLoginUser")
    @ResponseBody
    public ResponseEntity<String> login(HttpSession session){

        User loginUser = (User)session.getAttribute("loginUser");

        if(loginUser != null){
            return new ResponseEntity<>("LoginUser is not null, and loginUser.getPn() = " + loginUser.getPn(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("LoginUser is null", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpSession session){

        session.invalidate();

        return new ResponseEntity<>("Logout success", HttpStatus.OK);

    }

}