package com.example.bno2.mapper;

import com.example.bno2.model.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentMapper {

    List<Equipment> selectAllEquipments();

}