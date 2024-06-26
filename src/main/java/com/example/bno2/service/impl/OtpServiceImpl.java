package com.example.bno2.service.impl;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.LoginMapper;
import com.example.bno2.mapper.OtpMapper;
import com.example.bno2.mapper.UserMapper;
import com.example.bno2.service.OtpService;
import com.example.bno2.utils.OTPUtil;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.management.LockInfo;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpMapper otpMapper;

    @Autowired
    LoginMapper loginMapper;

    public OTPUtil otpUtil = new OTPUtil();

    @Override
    public ResponseEntity<String> otpRegister(HttpSession session) throws IOException, WriterException {

        User loginUser = (User)session.getAttribute("loginUser");
        int userPn = loginUser.getPn();

        if(otpMapper.checkQrBase64(userPn) == 0){

            if(otpMapper.checkSecretKey(userPn) == 0)
                otpMapper.addSecretKey(userPn, otpUtil.getSecretKey());

            String filePath = System.getProperty("user.home") + "/Desktop/image";
            File folder = new File(filePath);

            if(!folder.exists())
                folder.mkdirs();

            if(!folder.canWrite())
                folder.setWritable(true);

            otpUtil.getQRImage(otpUtil.getGoogleOTPAuthURL(otpMapper.getSecretKey(userPn), loginUser.getEmail(), "bnosoft"),filePath+"/" + userPn +"_QR.png", 900, 900);

            Path path = Paths.get(filePath+"/" + userPn +"_QR.png");
            byte[] imageData = Files.readAllBytes(path);
            String base64String = Base64.getEncoder().encodeToString(imageData);

            otpMapper.addQrBase64(userPn,base64String);

        }

        return new ResponseEntity<>(otpMapper.getQrBase64(userPn),HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> otpAuth(HttpSession session, String otp){

        User loginUser = (User)session.getAttribute("loginUser");

        if(loginUser == null) {
            int authUserPn = (int) session.getAttribute("authUserPn");
            String authUserotpKey = otpUtil.getTOTPCode(otpMapper.getSecretKey(authUserPn));

            if (authUserotpKey.equals(otp)) {

                loginMapper.addLoginHistory(authUserPn);


                if (otpMapper.otpIsRegistered(authUserPn) == 0)
                    otpMapper.updateOtpIsRegistered(authUserPn);

                String email = otpMapper.getEmailBySecretKey(authUserPn);
                session.setAttribute("resetPasswordEmail", email);

                return new ResponseEntity<>(email, HttpStatus.OK);


            } else
                return new ResponseEntity<>("인증번호가 일치하지 않습니다.", HttpStatus.OK);
        }

            else{
        int userPn = loginUser.getPn();

        String otpKey = otpUtil.getTOTPCode(otpMapper.getSecretKey(userPn));

        if(otpKey.equals(otp)) {

            loginMapper.addLoginHistory(userPn);


            if (otpMapper.otpIsRegistered(userPn) == 0)
                otpMapper.updateOtpIsRegistered(userPn);

            String email = otpMapper.getEmailBySecretKey(userPn);

            return new ResponseEntity<>("OTP 인증 성공", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("인증번호가 일치하지 않습니다.", HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<String> userAuth(HttpSession session,String name, String email, String con){


        User authUser = otpMapper.userAuth(name, email, con);

        if (authUser != null) {
            session.setAttribute("authUser", authUser);
            session.setAttribute("authUserPn", authUser.getPn()); // pn 값 추가 저장
            return new ResponseEntity<>("본인인증이 완료됐습니다. OTP 인증을 해주세요.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("일치하는 회원정보가 없습니다. 회원가입을 해주세요.", HttpStatus.OK);
        }
    }

}