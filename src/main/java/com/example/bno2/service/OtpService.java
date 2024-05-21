package com.example.bno2.service;

import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface OtpService {

    ResponseEntity<byte[]> otpRegister(HttpSession session) throws IOException, WriterException;

    ResponseEntity<String> otpAuth(HttpSession session, String otp);

}