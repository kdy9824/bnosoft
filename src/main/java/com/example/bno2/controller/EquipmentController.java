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
    @GetMapping("/equipment/select")
    @ResponseBody
    public List<Equipment> equipment(@RequestParam String cls, @RequestParam String model, @RequestParam String name) {
        List<Equipment> equipmentList = equipmentService.getAllEquipments(cls, model, name);
        return equipmentList;
    }

    @Operation(summary = "장비 추가")
    @PostMapping("/equipment/insert")
    public ResponseEntity<String> insertEquipment(@RequestBody Equipment equipment) {
        int rowsInserted = equipmentService.insertEquipment(equipment);
        if (rowsInserted == 1) {
            return new ResponseEntity<>("Equipment added successfully", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Failed to add equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "장비 업데이트")
    @PostMapping("/equipment/update")
    public ResponseEntity<String> updateEquipemt(@RequestBody Equipment equipment) {
        int rowsUpdated = equipmentService.updateEquipment(equipment);
        if (rowsUpdated > 0) {
            return new ResponseEntity<>("Equipment updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "장비 삭제")
    @PostMapping("/equipment/delete")
    public ResponseEntity<String> deleteEquipment(@RequestParam("UID") String UID) {
        int rowsDeleted = equipmentService.deleteEquipment(UID);
        if (rowsDeleted > 0) {
            return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to delete equipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}