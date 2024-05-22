package com.example.bno2.service.impl;

import com.example.bno2.dto.Code;
import com.example.bno2.dto.ProjectUser;
import com.example.bno2.mapper.CodeMapper;
import com.example.bno2.mapper.ProjectUserMapper;
import com.example.bno2.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    ProjectUserMapper projectUserMapper;

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

    @Override
    public List<ProjectUser> selectProjectUsers(String projectName, String userName, String stateCode) {

        List<ProjectUser> projectUserList = projectUserMapper.selectProjectUsers(projectName, userName, stateCode);

        for (ProjectUser projectUser : projectUserList) {
            projectUser.setStateCode(replaceStateText(projectUser.getStateCode()));
        }

        return projectUserList;

    }
    @Override
    public ResponseEntity<String> insertProjectTeam(String projectUid, int userPn, String role, String roleDetail) {

        if (projectUserMapper.insertProjectTeam(projectUid, userPn, role, roleDetail) > 0)
            return new ResponseEntity<>("프로젝트 팀원 추가 성공", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update ProjectUser", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateProjectUser(String projectUid, int userPn,String role, String roleDetail) {

        if (projectUserMapper.updateProjectUser(projectUid, userPn, role, roleDetail) > 0)
            return new ResponseEntity<>("ProjectUser updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update ProjectUser", HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @Override
//    public List<ProjectUser> selectProjectUsersData() {
//
//        return projectUserMapper.selectProjectUsersData();
//
//    }

    @Override
    public ResponseEntity<String> deleteProjectUser(String projectUid, String userPn) {

        if (projectUserMapper.deleteProjectUser(projectUid, userPn) > 0)
            return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to delete equipment", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}