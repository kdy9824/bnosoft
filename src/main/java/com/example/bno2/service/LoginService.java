package com.example.bno2.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    String login(HttpSession session, String email, String password);

    String logout(HttpSession session);

}