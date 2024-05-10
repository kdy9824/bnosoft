package com.example.bno2.mapper;

import com.example.bno2.dto.EUP;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EUPMapper {

    List<EUP> selectEquipmentUsers(String name, String project);

    List<EUP> selectEquipmentUsersByCls(String name, String project, String cls);

}