package com.example.bno2.service;

import com.example.bno2.mapper.EquipmentMapper;
import com.example.bno2.dto.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    public List<Equipment> selectEquipments(String model) {
        return equipmentMapper.selectEquipments(model);
    }

    public List<Equipment> selectEquipmentsByCls(String model, String equipClass) {
        return equipmentMapper.selectEquipmentsByCls(model,equipClass);
    }

    public int insertEquipment(Equipment equipment, int loginUserPn) {

        Map<String, Object> params = new HashMap<>();
        params.put("equipment",equipment);
        params.put("loginUserPn", loginUserPn);

        return equipmentMapper.insertEquipment(params);

    }
    public int insertEquipment(Equipment equipment) {
        return equipmentMapper.insertEquipment(equipment);
    }

    public int updateEquipment(Equipment equipment, int loginUserPn) {

        Map<String, Object> params = new HashMap<>();
        params.put("equipment",equipment);
        params.put("loginUserPn", loginUserPn);

        return equipmentMapper.updateEquipment(params);
    }
    public int updateEquipment(Equipment equipment) {
        return equipmentMapper.updateEquipment(equipment);
    }

    public int deleteEquipment(String equip_uid) {
        return equipmentMapper.deleteEquipment(equip_uid);
    }

}
