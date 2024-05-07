package com.example.bno2.mapper;

import com.example.bno2.dao.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {

    List<Project> selectProjectsByName(String pjtName);

    List<Project> selectProjectsByNameState(String pjtName, String pjtState);

    int insertProject(Map<String, Object> params);

    int updateProject(Map<String, Object> params);

}