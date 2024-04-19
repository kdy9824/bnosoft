package com.example.bno2.controller;

import com.example.bno2.model.Equipment;
import com.example.bno2.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/equipment")
    public String equipment(Model model) {

        List<Equipment> equipmentList = equipmentService.getAllEquipments();
        model.addAttribute("equipmentList", equipmentList);

        return "equipment";

    }

}