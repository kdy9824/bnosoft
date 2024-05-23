package com.example.bno2.service.impl;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.LoginMapper;
import com.example.bno2.mapper.OtpMapper;
import com.example.bno2.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    OtpMapper otpMapper;

    @Override
    public ResponseEntity<String> login(HttpSession session, String email, String password) {

        User loginUser = loginMapper.login(email, password);

        session.setAttribute("loginUser", loginUser);

        if(loginUser != null){
            int userPn = loginUser.getPn();
            Timestamp recentLoginDatetime = new Timestamp(loginMapper.getRecentLoginHistory(userPn).getTime());

            // recentLoginDatetime의 시간을 밀리초로 가져오기
            long recentLoginTimeMillis = recentLoginDatetime.getTime();

            // 현재 시간의 밀리초 구하기
            long currentTimeMillis = System.currentTimeMillis();

            // 30일에 해당하는 밀리초를 구해서 recentLoginTimeMillis에 더하기
            long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
            long thirtyDaysLaterInMillis = recentLoginTimeMillis + thirtyDaysInMillis;

            if(otpMapper.checkQrBase64(userPn) == 1 && otpMapper.otpIsRegistered(userPn) == 1){
                if(thirtyDaysLaterInMillis > currentTimeMillis){
                    loginMapper.addLoginHistory(userPn);
                    return new ResponseEntity<>("로그인하였습니다.", HttpStatus.OK);
                } else
                    return new ResponseEntity<>("OTP 인증이 필요합니다.", HttpStatus.OK);
            } else
                return new ResponseEntity<>("OTP 등록이 필요합니다.", HttpStatus.OK);

        } else
            return new ResponseEntity<>("입력한 이메일과 패스워드를 다시 확인해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> logout(HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        if(loginUser != null){
            loginMapper.addLogoutHistory(loginUser.getPn());
            session.invalidate();
        }

        return new ResponseEntity<>("로그아웃합니다.", HttpStatus.OK);

    }

}