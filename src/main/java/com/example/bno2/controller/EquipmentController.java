package com.example.bno2.controller;

import com.example.bno2.model.Equipment;
import com.example.bno2.model.User;
import com.example.bno2.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;

@Tag(name="장비")
@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Operation(summary = "장비 목록 조회")
    @GetMapping("/equipment")
    @ResponseBody
    public List<Equipment> equipment() {
        List<Equipment> equipmentList = equipmentService.getAllEquipments();
        return equipmentList;
    }


    @Operation(summary = "장비 업데이트")
    @PostMapping("/equipment/update")
    public String updateEquipment(@ModelAttribute Equipment equipment) {
        int rowsUpdated = equipmentService.updateEquipment(equipment);
        if (rowsUpdated > 0) {
            return "redirect:/equipment";
        } else {
            // Handle error case
            return "error";
        }
    }

    @Operation(summary = "장비 삭제")
    @PostMapping("/equipment/delete")
    public String deleteEquipment(@RequestParam("UID") String UID) {
        int rowsDeleted = equipmentService.deleteEquipment(UID);
        if (rowsDeleted > 0) {
            return "redirect:/equipment";
        } else {
            // Handle error case
            return "error";
        }
    }


}
