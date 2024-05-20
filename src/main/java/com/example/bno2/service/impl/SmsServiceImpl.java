package com.example.bno2.service.impl;

import com.example.bno2.service.SmsService;
import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsServiceImpl implements SmsService {

    final DefaultMessageService messageService;

    public SmsServiceImpl() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSPIOAOLL8OLWKC", "UW9GNJHPPKOY2ZZMGPVGEOUSATVHMF1U", "https://api.coolsms.co.kr");
    }

    @Override
    public SingleMessageSentResponse sendAuthCode(HttpSession session, String phoneNumber) {

        Random rand = new Random();
        String createdAuthCode = Integer.toString(100000 + rand.nextInt(900000));
        session.setAttribute("createdAuthCode", createdAuthCode);

        Message message = new Message();
        message.setFrom("01071089583");
        message.setTo(phoneNumber);
        message.setText("인증번호 " + createdAuthCode + "을(를) 입력하세요.");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));

    }

    @Override
    public ResponseEntity<String> sendAuthCodeWithoutCoolSms(HttpSession session) {

        Random rand = new Random();
        String createdAuthCode = Integer.toString(100000 + rand.nextInt(900000));
        session.setAttribute("createdAuthCode", createdAuthCode);

        return new ResponseEntity<>(createdAuthCode, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> verifyAuthCode(HttpSession session, String inputAuthCode) {

        String createdAuthCode = (String) session.getAttribute("createdAuthCode");

        if (createdAuthCode != null && createdAuthCode.equals(inputAuthCode))
            return new ResponseEntity<>("본인 인증이 완료되었습니다.", HttpStatus.OK);
        else
            return new ResponseEntity<>("인증번호가 일치하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}