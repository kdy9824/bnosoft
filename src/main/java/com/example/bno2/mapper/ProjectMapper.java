package com.example.bno2.mapper;

import com.example.bno2.model.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> selectAllProjects();

    int insertProject(Project project);

    int updateProject(Project project);

}