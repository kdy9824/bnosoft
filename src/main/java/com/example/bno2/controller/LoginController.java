package com.example.bno2.controller;

import com.example.bno2.dto.User;
import com.example.bno2.service.OtpService;
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
import java.sql.*;

@Tag(name = "로그인")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private OtpService otpService;

    @Operation(summary = "로그인")
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(HttpSession session, @RequestParam String email, @RequestParam String password) {

        User loginUser = loginService.login(email, password);
        int loginCount = loginService.loginCount(email, password);

        session.setAttribute("loginUser", loginUser);

        if(loginCount == 1){
            int userPn = loginUser.getPn();
            Timestamp recentLoginDatetime = new Timestamp(loginService.getRecentLoginHistory(userPn).getTime() - 9L*60*60*1000);

            // recentLoginDatetime의 시간을 밀리초로 가져오기
            long recentLoginTimeMillis = recentLoginDatetime.getTime();

            // 현재 시간의 밀리초 구하기
            long currentTimeMillis = System.currentTimeMillis();

            // 30일에 해당하는 밀리초를 구해서 recentLoginTimeMillis에 더하기
            long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
            long thirtyDaysLaterInMillis = recentLoginTimeMillis + thirtyDaysInMillis;

            if(otpService.otpIsRegistered(userPn) == 1){
                if(thirtyDaysLaterInMillis > currentTimeMillis){
                    loginService.addLoginHistory(userPn);
                    return new ResponseEntity<>("Login Success.", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Required OTP Auth.", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Required OTP Register.", HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("(Email, password) doesn't exist in user.", HttpStatus.OK);
        }

    }

    @Operation(summary = "로그인 유저 확인")
    @GetMapping("/checkLoginUser")
    @ResponseBody
    public ResponseEntity<String> login(HttpSession session){

        User loginUser = (User)session.getAttribute("loginUser");

        if(loginUser != null){
            return new ResponseEntity<>("LoginUser is not null, and loginUser.getPn() = " + loginUser.getPn() + ".", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("LoginUser is null.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // 로그인 성공했을 때만 호출되는 API로 loginSuccess라는 속성에 1을 저장
    @Operation(summary = "로그인 성공")
    @GetMapping("/loginSuccess")
    public ResponseEntity<String> loginSuccess(HttpSession session, @RequestParam String loginSuccess) {
        session.setAttribute("loginSuccess", loginSuccess);
        return new ResponseEntity<>("Login Success.", HttpStatus.OK);
    }

    @Operation(summary = "로그인 성공 검사")
    @GetMapping("/loginSuccessCheck")
    public ResponseEntity<String> loginCheck(HttpSession session) {
        if((session.getAttribute("loginSuccess").equals("1"))){
            return new ResponseEntity<>("LoginSuccess isn't 1.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("LoginSuccess is 1.", HttpStatus.OK);
        }
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpSession session){

        User loginUser = (User)session.getAttribute("loginUser");

        if(loginUser != null){
            loginService.addLogoutHistory(loginUser.getPn());
            session.invalidate();
        }

        return new ResponseEntity<>("Logout success.", HttpStatus.OK);

    }

}