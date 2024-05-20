package com.example.bno2.service.impl;

import com.example.bno2.dto.Code;
import com.example.bno2.mapper.CodeMapper;
import com.example.bno2.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    CodeMapper codeMapper;

    @Override
    public List<Code> getUserDeptCode() {
        return codeMapper.getUserDeptCode();
    }

    @Override
    public List<Code> getUserPosCode() {
        return codeMapper.getUserPosCode();
    }

    @Override
    public List<Code> getUserStateCode() {
        return codeMapper.getUserStateCode();
    }

    @Override
    public List<Code> getEquipmentClassCode() {
        return codeMapper.getEquipmentClassCode();
    }

    @Override
    public List<Code> getEquipmentStateCode() {
        return codeMapper.getEquipmentStateCode();
    }

    @Override
    public List<Code> getProjectStateCode() {
        return codeMapper.getProjectStateCode();
    }

}