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

    public List<Project> selectAllProjects() {
        return projectMapper.selectAllProjects();
    }

    public int insertProject(Project project) {
        return projectMapper.insertProject(project);
    }

    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }

}