package com.example.bno2.service;

import com.example.bno2.mapper.EquipmentMapper;
import com.example.bno2.mapper.EquipmentUserMapper;
import com.example.bno2.model.Equipment;
import com.example.bno2.model.EquipmentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentUserService {

    @Autowired
    private EquipmentUserMapper equipmentuserMapper;
    // 장비 사용자 전체 조회
    public List<EquipmentUser> getEquipmentUsers() {
        return equipmentuserMapper.selectEquipmentUsers();
    }

    // 장비사용자 수정 및 추가
    public int updateEquipmentUser(String uid, int pn) {
        return equipmentuserMapper.updateEquipmentUser(uid,pn);
    }
    // 장비사용자 삭제
    public int deleteEquipmentUser(String uid) {
        return equipmentuserMapper.deleteEquipmentUser(uid);
    }

}