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

    public List<User> selectUsers() {
        return userMapper.selectUsers();
    }

    public List<User> selectUsersByName(String name) {
        return userMapper.selectUsersByName(name);
    }

    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

}