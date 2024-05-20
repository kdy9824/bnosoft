package com.example.bno2.controller;

import com.example.bno2.service.LoginService;
import com.example.bno2.service.OtpService;
import com.example.bno2.utils.ImageToBlob;
import com.example.bno2.utils.OTPUtil;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Tag(name = "OTP")
@Controller
public class OtpController {

    @Autowired
    private OtpService otpService;

    public ImageToBlob imageToBlob = new ImageToBlob();
    public OTPUtil otpUtil = new OTPUtil();

    @Autowired
    private LoginService loginService;

    @Operation(summary = "OTP 등록")
    @GetMapping("/OTPRegist")
    @ResponseBody
    public ResponseEntity<byte[]> otpRegister(HttpSession session) throws IOException, WriterException {

        return otpService.otpRegister(session);

    }

    @Operation(summary = "OTP 인증")
    @GetMapping("/OTPAuth")
    @ResponseBody
    public ResponseEntity<String> googleOTPAuth(HttpSession session, @RequestParam String otp){

        return otpService.otpAuth(session, otp);

    }
}