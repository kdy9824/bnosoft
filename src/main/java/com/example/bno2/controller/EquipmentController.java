package com.example.bno2.controller;

import com.example.bno2.dto.Equipment;
import com.example.bno2.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="장비")
@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Operation(summary = "장비 목록 검색 조회")
    @GetMapping("/selectEquipmentsByCls")
    @ResponseBody
    public List<Equipment> selectEquipmentsByCls(@RequestParam(name="model" ,required = false) String model,@RequestParam(name="equipClass") String equipClass) {

        return equipmentService.selectEquipments(model, equipClass);

    }

    @Operation(summary = "장비 추가")
    @PostMapping("/insertEquipment")
    public ResponseEntity<String> insertEquipment(@RequestBody Equipment equipment, HttpSession session) {

        return equipmentService.insertEquipment(session, equipment);

    }

    @Operation(summary = "장비 업데이트")
    @PostMapping("/updateEquipment")
    public ResponseEntity<String> updateEquipment(@RequestBody Equipment equipment, HttpSession session) {

        return equipmentService.updateEquipment(session, equipment);

    }

    @Operation(summary = "장비 삭제")
    @PostMapping("/deleteEquipment")
    public ResponseEntity<String> deleteEquipment(@RequestParam("uid") String equip_uid) {

        return equipmentService.deleteEquipment(equip_uid);

    }

}