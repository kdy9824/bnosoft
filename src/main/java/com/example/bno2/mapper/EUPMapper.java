package com.example.bno2.mapper;

import com.example.bno2.dto.EUP;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EUPMapper {

    List<EUP> selectEquipmentUsers(String userName, String projectName, String equipClass);

    int updateEquipmentUser(String equip_uid,int user_pn);

    List<EUP> selectEquipmentUsersData();
}