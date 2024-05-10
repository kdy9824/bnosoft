package com.example.bno2.controller;

import com.example.bno2.dto.Code;
import com.example.bno2.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Tag(name = "코드")
@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    @Operation(summary = "사용자 부서 코드")
    @GetMapping("/getUserDeptCode")
    @ResponseBody
    public List<Code> getUserDeptCode() {
        return codeService.getUserDeptCode();
    }

    @Operation(summary = "사용자 직급 코드")
    @GetMapping("/getUserPosCode")
    @ResponseBody
    public List<Code> getUserPosCode() {
        return codeService.getUserPosCode();
    }

    @Operation(summary = "사용자 상태 코드")
    @GetMapping("/getUserStateCode")
    @ResponseBody
    public List<Code> getUserStateCode() {
        return codeService.getUserStateCode();
    }

    @Operation(summary = "장비 분류 코드")
    @GetMapping("/getEquipmentClassCode")
    @ResponseBody
    public List<Code> getEquipmentClassCode() {
        return codeService.getEquipmentClassCode();
    }

    @Operation(summary = "장비 상태 코드")
    @GetMapping("/getEquipmentStateCode")
    @ResponseBody
    public List<Code> getEquipmentStateCode() {
        return codeService.getEquipmentStateCode();
    }

    @Operation(summary = "프로젝트 상태 코드")
    @GetMapping("/getProjectStateCode")
    @ResponseBody
    public List<Code> getProjectStateCode() {
        return codeService.getProjectStateCode();
    }

}