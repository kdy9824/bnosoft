package com.example.bno2.service;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.Timestamp;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public User login(String email, String password) {
        return loginMapper.login(email, password);
    }

    public Timestamp getRecentLoginHistory(int userPn){
        return loginMapper.getRecentLoginHistory(userPn);
    }

    public int addLoginHistory(int userPn){
        return loginMapper.addLoginHistory(userPn);
    }

    public int addLogoutHistory(int userPn){
        return loginMapper.addLogoutHistory(userPn);
    }

}