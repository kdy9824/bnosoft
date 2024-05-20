package com.example.bno2.service;

import com.example.bno2.dto.Code;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CodeService {

    List<Code> getUserDeptCode();

    List<Code> getUserPosCode();

    List<Code> getUserStateCode();

    List<Code> getEquipmentClassCode();

    List<Code> getEquipmentStateCode();

    List<Code> getProjectStateCode();

}