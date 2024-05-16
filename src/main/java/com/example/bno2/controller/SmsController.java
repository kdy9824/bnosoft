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
// das
@Tag(name = "SMS")
@RestController
public class SmsController {

    final DefaultMessageService messageService;

    public SmsController(){
        this.messageService = NurigoApp.INSTANCE.initialize("NCSAKFXHFK099NGH", "SOLWJKARK1V4B5ZHFXK0Z61RIWNUEOOO", "https://api.coolsms.co.kr");
    }

    @Operation(summary = "인증번호 발송")
    @PostMapping("/sendAuthCode")
    public SingleMessageSentResponse sendAuthCode(HttpSession session, @RequestParam String phoneNumber) {

        Random rand = new Random();
        String createdAuthCode = Integer.toString(100000 + rand.nextInt(900000));
        session.setAttribute("createdAuthCode", createdAuthCode);

        Message message = new Message();
        message.setFrom("01062033145"); // API 키 & API 시크릿키가 01062033145로 생성한 API 키라서 01062033145만 가능
        message.setTo(phoneNumber); // 수신번호, FrontEnd에서 사용자가 직접 입력한 사용자 본인의 휴대폰 번호인 phoneNumber로 대체할 예정
        message.setText("인증번호 " + createdAuthCode + "을(를) 입력하세요.");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));

    }

    @Operation(summary = "인증번호 검증")
    @PostMapping("/verifyAuthCode")
    public ResponseEntity<String> verifyAuthCode(HttpSession session, @RequestParam String inputAuthCode) {

        String createdAuthCode = (String)session.getAttribute("createdAuthCode");

        if(createdAuthCode.equals(inputAuthCode)){
            return new ResponseEntity<>("AuthCode is Correct.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("AuthCode is incorrect.", HttpStatus.OK);
        }

    }

}