package com.example.bno2.service;

import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Blob;

@Service
public interface OtpService {

//    ResponseEntity<byte[]> otpRegister(HttpSession session) throws IOException, WriterException;

    ResponseEntity<String> otpRegister(HttpSession session) throws IOException, WriterException;

    ResponseEntity<String> otpAuth(HttpSession session, String otp);

    ResponseEntity<String> userAuth(HttpSession session,String name, String email, String con);

    }