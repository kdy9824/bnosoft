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

    public List<Equipment> getAllEquipments() {
        return equipmentMapper.selectAllEquipments();
    }

}