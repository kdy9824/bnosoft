package com.example.bno2.mapper;

import com.example.bno2.dto.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquipmentMapper {

    List<Equipment> selectEquipments(String model, String equipClass);

    int insertEquipment(Map<String, Object> params);

    int updateEquipment(Map<String, Object> params);

    int deleteEquipment(String equipUid);

}