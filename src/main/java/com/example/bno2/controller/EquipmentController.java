package com.example.bno2.controller;

import com.example.bno2.model.Equipment;
import com.example.bno2.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name="장비")
@Controller
public class EquipmentController {


    @Autowired
    private EquipmentService equipmentService;

    @Operation(summary = "dddd")
    @GetMapping("/equipment")
    public String equipment(Model model) {

        List<Equipment> equipmentList = equipmentService.getAllEquipments();
        model.addAttribute("equipmentList", equipmentList);

        return "equipment";

    }

}