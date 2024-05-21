package com.example.bno2.service;

import com.example.bno2.dto.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface JoinService {

    ResponseEntity<String> checkEmailDuplicate(String email);

    ResponseEntity<String> addUser(HttpSession session, User user);

}