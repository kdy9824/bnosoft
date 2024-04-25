package com.example.bno2.service;

import com.example.bno2.mapper.EquipmentMapper;
import com.example.bno2.mapper.ProjectMapper;
import com.example.bno2.model.Equipment;
import com.example.bno2.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> selectProjectsByNameProject(String name, String project) {
        return projectMapper.selectProjectsByNameProject(name,project);
    }

    public List<Project> selectProjectsByNameProjectState(String name, String project, String pjtState) {
        return projectMapper.selectProjectsByNameProjectState(name,project,pjtState);
    }

    public int insertProject(Project project) {
        return projectMapper.insertProject(project);
    }

    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }

}