package com.example.bno2.service;

import com.example.bno2.dao.User;
import com.example.bno2.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public User login(String email, String password) {
        return loginMapper.login(email, password);
    }

    public int loginCount(String email, String password) {
        return loginMapper.loginCount(email, password);
    }

}