package com.example.bno2.mapper;

import com.example.bno2.model.EquipmentUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentUserMapper {

    List<EquipmentUser> selectEquipmentUsers();

    int updateEquipmentUser(String uid, int pn);

    int deleteEquipmentUser(String uid);

}