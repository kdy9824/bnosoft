package com.example.bno2.service;

import com.example.bno2.dto.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface JoinService {

    String checkEmailDuplicate(String email);

    String addUser(HttpSession session, User user);

}