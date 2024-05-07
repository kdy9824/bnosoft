package com.example.bno2.mapper;

import com.example.bno2.dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    User login(String email, String password);

    int loginCount(String email, String password);

}