package com.example.bno2.service;

import com.example.bno2.mapper.ProjectMapper;
import com.example.bno2.dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> selectProjectsByName(String projectName) {
        return projectMapper.selectProjectsByName(projectName);
    }

    public List<Project> selectProjectsByNameState(String projectName, String stateCode) {
        return projectMapper.selectProjectsByNameState(projectName, stateCode);
    }

    public int insertProject(Project project, int loginUserPn) {

        Map<String, Object> params = new HashMap<>();
        params.put("project",project);
        params.put("loginUserPn",loginUserPn);

        return projectMapper.insertProject(params);

    }

    public int updateProject(Project project, int loginUserPn) {

        Map<String, Object> params = new HashMap<>();
        params.put("project",project);
        params.put("loginUserPn",loginUserPn);

        return projectMapper.updateProject(params);

    }

}