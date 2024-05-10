package com.example.bno2.controller;

import com.example.bno2.dto.EUP;
import com.example.bno2.service.EUPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EUP> selectEquipmentUsersByCls(@RequestParam String cls,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String project) {

        List<EUP> equipmentUserList;

        if(cls.equals("ALL")){
            equipmentUserList = eupService.selectEquipmentUsers(name,project);
        } else {
            equipmentUserList = eupService.selectEquipmentUsersByCls(name, project,cls);
        }

        // 코드화된 데이터를 텍스트로 대체
        for (EUP equipmentUser : equipmentUserList) {
            replaceCodeToText(equipmentUser);
        }

        return equipmentUserList;

    }

//    @Operation(summary = "장비사용자 수정")
//    @PostMapping("/updateEquipmentUser")
//    public ResponseEntity<String> updateEquipmentUser(@RequestParam String uid, @RequestParam int pn) {
//        int rowsUpdated = eupService.updateEquipmentUser(uid,pn);
//        if (rowsUpdated > 0) {
//            return new ResponseEntity<>("EquipmentUser updated successfully", HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>("Failed to update EquipmentUser", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
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