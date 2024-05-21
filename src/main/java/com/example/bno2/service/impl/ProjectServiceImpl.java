package com.example.bno2.service.impl;

import com.example.bno2.dto.Code;
import com.example.bno2.dto.Project;
import com.example.bno2.dto.User;
import com.example.bno2.mapper.CodeMapper;
import com.example.bno2.mapper.ProjectMapper;
import com.example.bno2.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    CodeMapper codeMapper;

    // state 값에 따라 텍스트를 대체하는 메서드
    private String replaceStateText(String state) {

        List<Code> codeList = codeMapper.getProjectStateCode();

        for(Code code : codeList){
            if(state.equals(code.getDetailClassCode()))
                return code.getDetailDesc();
        }

        return state;

    }

    public List<Project> selectProjectsByName(String projectName, String stateCode){

        List<Project> projectList = projectMapper.selectProjectsByName(projectName, stateCode);

        for (Project project : projectList)
            project.setStateCode(replaceStateText(project.getStateCode()));

        return projectList;

    }

    public ResponseEntity<String> insertProject(Project project, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        Map<String, Object> params = new HashMap<>();
        params.put("project",project);
        params.put("loginUserPn",loginUser.getPn());

        if (projectMapper.insertProject(params) > 0)
            return new ResponseEntity<>("Project inserted successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to insert project", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public ResponseEntity<String> updateProject(Project project, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        Map<String, Object> params = new HashMap<>();
        params.put("project",project);
        params.put("loginUserPn",loginUser.getPn());

        if (projectMapper.updateProject(params) > 0)
            return new ResponseEntity<>("프로젝트 성공", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update project", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}