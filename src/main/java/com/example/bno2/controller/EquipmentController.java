package com.example.bno2.controller;

import com.example.bno2.model.Equipment;
import com.example.bno2.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "장비 목록 조회")
    @GetMapping("/selectEquipments")
    @ResponseBody
    public List<Equipment> selectEquipments(@RequestParam(required = false) String cls, @RequestParam(required = false) String model, @RequestParam(required = false) String name) {
        List<Equipment> equipmentList = equipmentService.selectEquipments(cls, model, name);

        for(Equipment equipment : equipmentList) {
            equipment.setCls(replaceClsText(equipment.getCls()));
            equipment.setState(replaceStateText(equipment.getState()));
        }

        return equipmentList;
    }

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

    @Operation(summary = "장비 추가")
    @PostMapping("/insertEquipment")
    public ResponseEntity<String> insertEquipment(@RequestBody Equipment equipment) {
        int rowsInserted = equipmentService.insertEquipment(equipment);
        if (rowsInserted > 0) {
            return new ResponseEntity<>("Equipment inserted successfully", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Failed to insert equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "장비 업데이트")
    @PostMapping("/updateEquipment")
    public ResponseEntity<String> updateEquipment(@RequestBody Equipment equipment) {
        int rowsUpdated = equipmentService.updateEquipment(equipment);
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