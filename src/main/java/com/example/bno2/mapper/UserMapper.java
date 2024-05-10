package com.example.bno2.mapper;

import com.example.bno2.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<User> selectUsersByName(String name);

    List<User> selectUsersByNameDept(String name, String dept);

    int insertUser(Map<String, Object> params);

    int updateUser(Map<String, Object> params);

}