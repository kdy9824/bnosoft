package com.example.bno2.mapper;

import com.example.bno2.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    int checkEmailDuplicate(String email);

    int addUser(User user);

}