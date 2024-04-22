package com.example.bno2.controller;

import com.example.bno2.model.Equipment;
import com.example.bno2.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tag(name="장비")
@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Operation(summary = "장비 리스트 출력")
    @GetMapping("/equipment")
    @ResponseBody
    public List<Equipment> sortByDept() {

        List<Equipment> equipmentList = equipmentService.getAllEquipments();

        return equipmentList;

    }

}