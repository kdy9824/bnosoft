package com.example.bno2.service.impl;

import com.example.bno2.dto.Code;
import com.example.bno2.dto.Equipment;
import com.example.bno2.dto.User;
import com.example.bno2.mapper.CodeMapper;
import com.example.bno2.mapper.EquipmentMapper;
import com.example.bno2.service.EquipmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;

    @Autowired
    CodeMapper codeMapper;

    // cls 값에 따라 텍스트를 대체하는 메서드
    private String replaceClsText(String cls) {

        List<Code> codeList = codeMapper.getEquipmentClassCode();

        for(Code code : codeList){
            if(cls.equals(code.getDetailClassCode()))
                return code.getDetailDesc();
        }

        return cls;

    }

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {

        List<Code> codeList = codeMapper.getEquipmentStateCode();

        for(Code code : codeList){
            if(state.equals(code.getDetailClassCode()))
                return code.getDetailDesc();
        }

        return state;

    }

    private void replaceCodeToText(Equipment equipment){
        equipment.setEquipClass(replaceClsText(equipment.getEquipClass()));
        equipment.setStateCode(replaceStateText(equipment.getStateCode()));
    }

    @Override
    public List<Equipment> selectEquipments(String model, String equipClass) {
        List<Equipment> equipmentList = equipmentMapper.selectEquipments(model, equipClass);
//        for (Equipment equipment : equipmentList)
//            replaceCodeToText(equipment);

        return equipmentList;

    }

    @Override
    public ResponseEntity<String> insertEquipment(HttpSession session, Equipment equipment) {

        User loginUser = (User)session.getAttribute("loginUser");

        Map<String, Object> params = new HashMap<>();
        params.put("equipment",equipment);
        params.put("loginUserPn",loginUser.getPn());

        if(!equipment.getEquipUid().isEmpty())
            return new ResponseEntity<>("이미 존재하는 장비 입니다.",HttpStatus.INTERNAL_SERVER_ERROR);

        if(equipmentMapper.insertEquipment(params) > 0)
            return new ResponseEntity<>("Equipment inserted successfully",HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to insert equipment",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateEquipment(HttpSession session, Equipment equipment) {

        User loginUser = (User)session.getAttribute("loginUser");

        Map<String, Object> params = new HashMap<>();
        params.put("equipment",equipment);
        params.put("loginUserPn",loginUser.getPn());

        if (equipmentMapper.updateEquipment(params) > 0)
            return new ResponseEntity<>("Equipment updated successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to update equipment", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> deleteEquipment(String equipUid) {

        if (equipmentMapper.deleteEquipment(equipUid) > 0)
            return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to delete equipment", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}