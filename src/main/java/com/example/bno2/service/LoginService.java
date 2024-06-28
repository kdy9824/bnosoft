package com.example.bno2.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    ResponseEntity<String> login(HttpSession session, String email, String password);

    ResponseEntity<String> logout(HttpSession session);

    ResponseEntity<String> resetPassword(HttpSession session, String newPassword);

}