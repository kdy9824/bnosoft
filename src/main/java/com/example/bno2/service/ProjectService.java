package com.example.bno2.service;

import com.example.bno2.mapper.ProjectMapper;
import com.example.bno2.dao.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> selectProjectsByName(String pjtName) {
        return projectMapper.selectProjectsByName(pjtName);
    }

    public List<Project> selectProjectsByNameState(String pjtName, String pjtState) {
        return projectMapper.selectProjectsByNameState(pjtName, pjtState);
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