package com.example.bno2.mapper;

import com.example.bno2.dto.Code;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {

    List<Code> getUserDeptCode();

    List<Code> getUserPosCode();

    List<Code> getUserStateCode();

    List<Code> getEquipmentClassCode();

    List<Code> getEquipmentStateCode();

    List<Code> getProjectStateCode();

}