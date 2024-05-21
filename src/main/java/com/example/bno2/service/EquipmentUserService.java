package com.example.bno2.service;

import com.example.bno2.dto.EquipmentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentUserService {    // 장비 사용자 전체 조회

    // 장비 사용자 cls별 조회
    List<EquipmentUser> selectEquipmentUsers(String userName, String projectName, String equipClass);

    // 장비사용자 수정 및 추가
    ResponseEntity<String> updateEquipmentUser(String equipUid, int userPn);

    // 장비 사용자 전체 조회
    List<EquipmentUser> selectEquipmentUsersData();

//    // 장비사용자 삭제
//    int deleteEquipmentUser(String uid);

}