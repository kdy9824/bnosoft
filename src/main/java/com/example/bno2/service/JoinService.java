package com.example.bno2.service;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.JoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private JoinMapper joinMapper;

    public int checkEmailDuplicate(String email) {
        return joinMapper.checkEmailDuplicate(email);
    }

    public int addUser(User user) {
        return joinMapper.addUser(user);
    }

}