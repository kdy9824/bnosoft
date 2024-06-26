package com.example.bno2.service;

import com.example.bno2.dto.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    List<User> selectUsersByName(String name, String deptCode, String posCode);

    ResponseEntity<String> addUser(User user, HttpSession session);

    ResponseEntity<String> updateUser(User user, HttpSession session);
    ResponseEntity<String> updateUserState(String stateCode, int pn);
}