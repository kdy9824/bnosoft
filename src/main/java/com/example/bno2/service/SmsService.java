package com.example.bno2.service;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.stereotype.Service;

@Service
public interface SmsService {

    SingleMessageSentResponse sendAuthCode(HttpSession session, String phoneNumber);

    String sendAuthCodeWithoutCoolSms(HttpSession session, String phoneNumber);

    String verifyAuthCode(HttpSession session, String inputAuthCode);

}