package com.example.bno2.service;

import com.example.bno2.mapper.EquipmentMapper;
import com.example.bno2.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    public List<Equipment> selectEquipments(String cls, String model, String name) {
        return equipmentMapper.selectEquipments(cls, model, name);
    }

    public int insertEquipment(Equipment equipment) {
        return equipmentMapper.insertEquipment(equipment);
    }


    public int updateEquipment(Equipment equipment) {
        return equipmentMapper.updateEquipment(equipment);
    }

    public int deleteEquipment(String UID) {
        return equipmentMapper.deleteEquipment(UID);
    }

}
