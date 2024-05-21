package com.example.bno2.controller;

import com.example.bno2.dto.EquipmentUser;
import com.example.bno2.service.EquipmentUserService;
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
public class EquipmentUserController {

    @Autowired
    private EquipmentUserService equipmentUserService;

    @Operation(summary = "장비사용자 리스트 출력")
    @GetMapping("/selectEquipmentUsersByCls")
    @ResponseBody
    public List<EquipmentUser> selectEquipmentUsersByCls(
                                               @RequestParam(name="userName",required = false) String userName,
                                               @RequestParam(name="projectName",required = false) String projectName,@RequestParam(name="equipClass") String equipClass) {

        return equipmentUserService.selectEquipmentUsers(userName, projectName, equipClass);

    }

    @Operation(summary = "장비사용자 수정")
    @PostMapping("/updateEquipmentUser")
    public ResponseEntity<String> updateEquipmentUser(@RequestParam(name="equipUid",required = false) String equipUid,@RequestParam(name="userPn",required = false) int userPn) {

        return equipmentUserService.updateEquipmentUser(equipUid, userPn);

    }

    @Operation(summary = "장비사용자 리스트 데이터 출력")
    @GetMapping("/selectEquipmentUsersData")
    @ResponseBody
    public List<EquipmentUser> selectEquipmentUsersData() {

        return equipmentUserService.selectEquipmentUsersData();

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