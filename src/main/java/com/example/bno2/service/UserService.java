package com.example.bno2.service;

import com.example.bno2.mapper.UserMapper;
import com.example.bno2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsersOrderByPos() {
        return userMapper.selectAllUsersOrderByPos();
    }

    public List<User> getAllUsersOrderByName() {
        return userMapper.selectAllUsersOrderByName();
    }

    public List<User> getAllUsersOrderByDept() {
        return userMapper.selectAllUsersOrderByDept();
    }

    public List<User> getAllUsersOrderByName2(String inputName) {
        return userMapper.selectAllUsersOrderByName2(inputName);
    }

}