package com.example.bno2.service;

import com.example.bno2.mapper.EUPMapper;
import com.example.bno2.dao.EUP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EUPService {

    @Autowired
    private EUPMapper eupMapper;
    // 장비 사용자 전체 조회
    public List<EUP> selectEquipmentUsers(String name, String project) {
        return eupMapper.selectEquipmentUsers(name, project);
    }

    // 장비 사용자 cls별 조회
    public List<EUP> selectEquipmentUsersByCls(String name, String project, String cls) {
        return eupMapper.selectEquipmentUsersByCls(name, project, cls);
    }

//    // 장비사용자 수정 및 추가
//    public int updateEquipmentUser(String uid, int pn) {
//        return eupMapper.updateEquipmentUser(uid,pn);
//    }
//
//    // 장비사용자 삭제
//    public int deleteEquipmentUser(String uid) {
//        return eupMapper.deleteEquipmentUser(uid);
//    }

}