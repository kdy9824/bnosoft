package com.example.bno2.service;



import com.example.bno2.dto.ProjectUser;
import com.example.bno2.mapper.ProjectUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectUserService {

    @Autowired
    private ProjectUserMapper projectuserMapper;

    public List<ProjectUser> selectProjectUsers(String project_name,String user_name, String stateCode) {
        return projectuserMapper.selectProjectUsers(project_name,user_name,stateCode);
    }
    // 장비사용자 수정 및 추가
    public int updateProjectUser(String project_uid, int user_pn, int new_user_pn) {
        return projectuserMapper.updateProjectUser(project_uid,user_pn,new_user_pn);
    }

    // 장비 사용자 전체 조회
    public List<ProjectUser> selectProjectUsersData() {
        return projectuserMapper.selectProjectUsersData();
    }
    public int deleteProjectUser(String project_uid, String user_name) {
        return projectuserMapper.deleteProjectUser(project_uid, user_name);
    }
}
