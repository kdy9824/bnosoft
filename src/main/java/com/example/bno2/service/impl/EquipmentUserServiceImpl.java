package com.example.bno2.service.impl;

import com.example.bno2.dto.Code;
import com.example.bno2.dto.Equipment;
import com.example.bno2.dto.EquipmentUser;
import com.example.bno2.dto.User;
import com.example.bno2.mapper.CodeMapper;
import com.example.bno2.mapper.EquipmentMapper;
import com.example.bno2.mapper.EquipmentUserMapper;
import com.example.bno2.service.EquipmentUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentUserServiceImpl implements EquipmentUserService {

    @Autowired
    EquipmentUserMapper equipmentUserMapper;

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

    private void replaceCodeToText(EquipmentUser equipmentUser){
        equipmentUser.setEquipClass(replaceClsText(equipmentUser.getEquipClass()));
        equipmentUser.setEquipmentStateCode(replaceStateText(equipmentUser.getEquipmentStateCode()));
    }

    @Override
    public List<EquipmentUser> selectEquipmentUsers(String userName, String projectName, String equipClass) {

        List<EquipmentUser> equipmentUserList = equipmentUserMapper.selectEquipmentUsers(userName, projectName, equipClass);

        for(EquipmentUser equipmentUser : equipmentUserList)
            replaceCodeToText(equipmentUser);

        return equipmentUserList;

    }

    @Override
    public ResponseEntity<String> updateEquipmentUser(String equipUid, int userPn) {

        if (equipmentUserMapper.updateEquipmentUser(equipUid,userPn) > 0)
            return new ResponseEntity<>("EquipmentUser updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update EquipmentUser", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public List<EquipmentUser> selectEquipmentUsersData() {

        return equipmentUserMapper.selectEquipmentUsersData();

    }
}