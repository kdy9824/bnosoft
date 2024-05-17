package com.example.bno2.service;

import com.example.bno2.mapper.UserMapper;
import com.example.bno2.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectUsersByName(String name) {
        return userMapper.selectUsersByName(name);
    }

    public List<User> selectUsersByNameDept(String name, String deptCode) {
        return userMapper.selectUsersByNameDept(name, deptCode);
    }

    public int addUser(User user, int loginUserPn) {

        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("loginUserPn", loginUserPn);

        return userMapper.insertUser(params);

    }

    public int updateUser(User user, int loginUserPn) {

        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("loginUserPn", loginUserPn);

        return userMapper.updateUser(params);

    }


}