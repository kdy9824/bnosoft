package com.example.bno2.service;

import com.example.bno2.mapper.EUPMapper;
import com.example.bno2.dto.EUP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EUPService {

    @Autowired
    private EUPMapper eupMapper;
    // 장비 사용자 전체 조회
    public List<EUP> selectEquipmentUsers(String userName, String projectName, String equipClass) {
        return eupMapper.selectEquipmentUsers(userName, projectName, equipClass);
    }

//    // 장비 사용자 cls별 조회
//    public List<EUP> selectEquipmentUsersByCls(String userName, String projectName, String equipClass) {
//        return eupMapper.selectEquipmentUsersByCls(userName, projectName, equipClass);
//    }

    // 장비사용자 수정 및 추가
    public int updateEquipmentUser(String uid, int user_pn) {
        return eupMapper.updateEquipmentUser(uid, user_pn);
    }

    // 장비 사용자 전체 조회
    public List<EUP> selectEquipmentUsersData() {
        return eupMapper.selectEquipmentUsersData();
    }
//
//    // 장비사용자 삭제
//    public int deleteEquipmentUser(String uid) {
//        return eupMapper.deleteEquipmentUser(uid);
//    }

}