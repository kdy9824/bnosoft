package com.example.bno2.mapper;

import com.example.bno2.dto.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {

    List<Project> selectProjectsByName(String projectName, String projectStateCode);

    int insertProject(Map<String, Object> params);

    int updateProject(Map<String, Object> params);

    int deleteProject(String projectUid);

    int updateUserStateForCompletedProject(String projectUid);
}