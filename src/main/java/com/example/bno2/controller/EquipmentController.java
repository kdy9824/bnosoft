package com.example.bno2.controller;

import com.example.bno2.dao.Equipment;
import com.example.bno2.dao.User;
import com.example.bno2.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="장비")
@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

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

    private void replaceCodeToText(Equipment equipment){
        equipment.setEquipClass(replaceClsText(equipment.getEquipClass()));
        equipment.setStateCode(replaceStateText(equipment.getStateCode()));
    }

    @Operation(summary = "장비 목록 검색 조회")
    @GetMapping("/selectEquipmentsByCls")
    @ResponseBody
    public List<Equipment> selectEquipmentsByCls(@RequestParam String cls, @RequestParam(required = false) String model) {
        List<Equipment> equipmentList;

        if(cls.equals("ALL")){
            equipmentList = equipmentService.selectEquipments(model);
        } else {
            equipmentList = equipmentService.selectEquipmentsByCls(model, cls);
        }
        // 코드화된 데이터를 텍스트로 대체
        for (Equipment equipment : equipmentList) {
            replaceCodeToText(equipment);
        }

        return equipmentList;
    }

    @Operation(summary = "장비 추가")
    @PostMapping("/insertEquipment")
    public ResponseEntity<String> insertEquipment(@RequestBody Equipment equipment, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        int loginUserPn = loginUser.getPn();

        int rowsInserted = equipmentService.insertEquipment(equipment, loginUserPn);

        if (rowsInserted > 0) {
            return new ResponseEntity<>("Equipment inserted successfully", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Failed to insert equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "장비 업데이트")
    @PostMapping("/updateEquipment")
    public ResponseEntity<String> updateEquipment(@RequestBody Equipment equipment, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        int loginUserPn = loginUser.getPn();

        int rowsUpdated = equipmentService.updateEquipment(equipment, loginUserPn);

        if (rowsUpdated > 0) {
            return new ResponseEntity<>("Equipment updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "장비 삭제")
    @PostMapping("/deleteEquipment")
    public ResponseEntity<String> deleteEquipment(@RequestParam("UID") String UID) {
        int rowsDeleted = equipmentService.deleteEquipment(UID);
        if (rowsDeleted > 0) {
            return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to delete equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}