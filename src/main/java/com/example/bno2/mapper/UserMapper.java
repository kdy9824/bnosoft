package com.example.bno2.mapper;

import com.example.bno2.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectUsers(String name);

    int insertUser(User user);

    int updateUser(User user);

}