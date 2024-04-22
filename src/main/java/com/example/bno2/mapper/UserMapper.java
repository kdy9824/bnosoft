package com.example.bno2.mapper;

import com.example.bno2.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAllUsersOrderByPos();

    List<User> selectAllUsersOrderByName();

    List<User> selectAllUsersOrderByDept();

    List<User> selectAllUsersOrderByName2(String inputName);

    int insertUser(String email, String name, String dept, String con, String pos, String state);

    boolean insertUser2(User user);

    int updateUser(User user);

}