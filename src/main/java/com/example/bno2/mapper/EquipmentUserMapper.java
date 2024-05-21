package com.example.bno2.mapper;

import com.example.bno2.dto.EquipmentUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentUserMapper {

    List<EquipmentUser> selectEquipmentUsers(String userName, String projectName, String equipClass);

    int updateEquipmentUser(String equipUid,int userPn);

    List<EquipmentUser> selectEquipmentUsersData();
}