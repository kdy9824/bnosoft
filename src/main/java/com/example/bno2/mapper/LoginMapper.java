package com.example.bno2.mapper;

import com.example.bno2.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface LoginMapper {

    User login(String email, String password);

    int loginCount(String email, String password);

    Timestamp getRecentLoginHistory(int userPn);

    int addLoginHistory(int userPn);

    int addLogoutHistory(int userPn);

}