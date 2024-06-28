package com.example.bno2.controller;

import com.example.bno2.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;

@Tag(name = "SMS")
@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @Operation(summary = "인증번호 발송")
    @PostMapping("/sendAuthCode")
    public SingleMessageSentResponse sendAuthCode(HttpSession session, @RequestParam String phoneNumber) {

        return smsService.sendAuthCode(session, phoneNumber);

    }

    @Operation(summary = "CoolSMS 안쓰고 인증번호 발송")
    @PostMapping("/sendAuthCodeWithoutCoolSms")
    public ResponseEntity<String> sendAuthCodeWithoutCoolSms(HttpSession session) {

        return smsService.sendAuthCodeWithoutCoolSms(session);

    }

    @Operation(summary = "인증번호 검증")
    @PostMapping("/verifyAuthCode")
    public ResponseEntity<String> verifyAuthCode(HttpSession session, @RequestParam String inputAuthCode) {

        return smsService.verifyAuthCode(session,inputAuthCode);

    }

}