package com.example.bno2.mapper;

import com.example.bno2.dto.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    
    List<User> selectUsersByNameDept(String name, String deptCode, String posCode);

    int insertUser(Map<String, Object> params);

    int updateUser(Map<String, Object> params);
    int updateUserState(String stateCode, int pn);
}