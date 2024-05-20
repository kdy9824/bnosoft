package com.example.bno2.service;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SmsService {

    SingleMessageSentResponse sendAuthCode(HttpSession session, String phoneNumber);

    ResponseEntity<String> sendAuthCodeWithoutCoolSms(HttpSession session);

    ResponseEntity<String> verifyAuthCode(HttpSession session, String inputAuthCode);

}