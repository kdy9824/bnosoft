package com.example.bno2.service;

import com.example.bno2.mapper.UserMapper;
import com.example.bno2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectUsers(String inputName) {
        return userMapper.selectUsers(inputName);
    }

    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

}