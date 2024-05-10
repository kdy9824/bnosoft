package com.example.bno2.service;

import com.example.bno2.dto.Code;
import com.example.bno2.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Autowired
    private CodeMapper codeMapper;

    public List<Code> getUserDeptCode() {
        return codeMapper.getUserDeptCode();
    }

    public List<Code> getUserPosCode() {
        return codeMapper.getUserPosCode();
    }

    public List<Code> getUserStateCode() {
        return codeMapper.getUserStateCode();
    }

    public List<Code> getEquipmentClassCode() {
        return codeMapper.getEquipmentClassCode();
    }

    public List<Code> getEquipmentStateCode() {
        return codeMapper.getEquipmentStateCode();
    }

    public List<Code> getProjectStateCode() {
        return codeMapper.getProjectStateCode();
    }

}