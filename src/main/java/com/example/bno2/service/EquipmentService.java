package com.example.bno2.service;

import com.example.bno2.dto.Equipment;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EquipmentService {

    List<Equipment> selectEquipments(String model, String equipClass);

    ResponseEntity<String> insertEquipment(HttpSession session, Equipment equipment);

    ResponseEntity<String> updateEquipment(HttpSession session, Equipment equipment);

    ResponseEntity<String> deleteEquipment(String equipUid);

}