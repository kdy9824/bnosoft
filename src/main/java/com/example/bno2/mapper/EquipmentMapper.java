package com.example.bno2.mapper;

import com.example.bno2.model.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EquipmentMapper {

    List<Equipment> selectAllEquipments();

    int updateEquipment(Equipment equipment);

    int deleteEquipment(@Param("UID") String UID);
}