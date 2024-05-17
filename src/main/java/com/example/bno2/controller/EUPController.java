package com.example.bno2.controller;

import com.example.bno2.dto.EUP;
import com.example.bno2.service.EUPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name="장비-사용자")
@Controller
public class EUPController {

    @Autowired
    private EUPService eupService;

    // cls 값에 따라 텍스트를 대체하는 메서드
    private String replaceClsText(String cls) {
        if ("NTB".equals(cls)) {
            return "노트북";
        } else if ("MON".equals(cls)) {
            return "모니터";
        } else if ("ETC".equals(cls)) {
            return "기타";
        } else {
            return cls;
        }
    }

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {
        if ("USE".equals(state)) {
            return "사용";
        } else if ("NON".equals(state)) {
            return "사용대기";
        } else if ("FIX".equals(state)) {
            return "고장";
        } else {
            return state;
        }
    }
    private void replaceCodeToText(EUP eup){
        eup.setEquipClass(replaceClsText(eup.getEquipClass()));
        eup.setEquipmentStateCode(replaceStateText(eup.getEquipmentStateCode()));
    }

    @Operation(summary = "장비사용자 리스트 출력")
    @GetMapping("/selectEquipmentUsersByCls")
    @ResponseBody
    public List<EUP> selectEquipmentUsersByCls(
                                               @RequestParam(name="userName",required = false) String userName,
                                               @RequestParam(name="projectName",required = false) String projectName,@RequestParam(name="equipClass") String equipClass) {

        List<EUP> equipmentUserList;

        if(equipClass.equals("ALL")){
            equipmentUserList = eupService.selectEquipmentUsers(userName,projectName);
        } else {
            equipmentUserList = eupService.selectEquipmentUsersByCls(userName, projectName,equipClass);
        }
        // 쿼리 하나로 합칙시 (다이나믹 쿼리 if문 써서)

        // 코드화된 데이터를 텍스트로 대체
        for (EUP equipmentUser : equipmentUserList) {
            replaceCodeToText(equipmentUser);
        }

        return equipmentUserList;

    }

    @Operation(summary = "장비사용자 수정")
    @PostMapping("/updateEquipmentUser")
    public ResponseEntity<String> updateEquipmentUser(@RequestParam(name="equip_uid",required = false) String equip_uid,@RequestParam(name="user_pn",required = false) int user_pn) {
        System.out.println("equipmentUser : ");
        int rowsUpdated = eupService.updateEquipmentUser(equip_uid,user_pn);
        if (rowsUpdated > 0) {
            return new ResponseEntity<>("EquipmentUser updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update EquipmentUser", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "장비사용자 리스트 데이터 출력")
    @GetMapping("/selectEquipmentUsersData")
    @ResponseBody
    public List<EUP> selectEquipmentUsersData() {
        return eupService.selectEquipmentUsersData();

    }

//    @Operation(summary = "장비사용자 삭제")
//    @PostMapping("/deleteEquipmentUser")
//    public ResponseEntity<String> deleteEquipmentUser(@RequestParam String uid) {
//        int rowsDeleted = eupService.deleteEquipmentUser(uid);
//        if (rowsDeleted > 0) {
//            return new ResponseEntity<>("EquipmentUser updated successfully", HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>("Failed to update EquipmentUser", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}