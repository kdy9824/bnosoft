package com.example.bno2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

import java.util.Random;

@Tag(name = "SMS")
@RestController
public class SmsController {

    final DefaultMessageService messageService;

    public SmsController(){
        this.messageService = NurigoApp.INSTANCE.initialize("NCSPIOAOLL8OLWKC", "UW9GNJHPPKOY2ZZMGPVGEOUSATVHMF1U", "https://api.coolsms.co.kr");
    }

    @Operation(summary = "인증번호 발송")
    @PostMapping("/sendAuthCode")
    public SingleMessageSentResponse sendAuthCode(HttpSession session, @RequestParam String phoneNumber) {
        Random rand = new Random();
        String createdAuthCode = Integer.toString(100000 + rand.nextInt(900000));
        session.setAttribute("createdAuthCode", createdAuthCode);

        Message message = new Message();
        message.setFrom("01071089583");
        message.setTo(phoneNumber);
        message.setText("인증번호 " + createdAuthCode + "을(를) 입력하세요.");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    }

    @Operation(summary = "CoolSMS 안쓰고 인증번호 발송")
    @PostMapping("/sendAuthCodeWithoutCoolSms")
    public ResponseEntity<String> sendAuthCodeWithoutCoolSms(HttpSession session) {
        Random rand = new Random();
        String createdAuthCode = Integer.toString(100000 + rand.nextInt(900000));
        session.setAttribute("createdAuthCode", createdAuthCode);

        return new ResponseEntity<>(createdAuthCode, HttpStatus.OK);
    }

    @Operation(summary = "인증번호 검증")
    @PostMapping("/verifyAuthCode")
    public ResponseEntity<String> verifyAuthCode(HttpSession session, @RequestParam String inputAuthCode) {
        String createdAuthCode = (String) session.getAttribute("createdAuthCode");

        if (createdAuthCode != null && createdAuthCode.equals(inputAuthCode)) {
            return new ResponseEntity<>("본인 인증이 완료되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("인증번호가 일치하지 않습니다.", HttpStatus.OK);
        }
    }

}