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

    public int addUser(String email, String name, String dept, String con, String pos, String state) {
        return userMapper.insertUser(email, name, dept, con, pos, state);
    }

    @Transactional
    public boolean addUser(User user) {
        boolean rowsInserted = false;
        System.out.println(user);
        rowsInserted = userMapper.insertUser2(user);

        return rowsInserted;
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

}