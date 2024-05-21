package com.example.bno2.service;

import com.example.bno2.dto.ProjectUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProjectUserService {

    List<ProjectUser> selectProjectUsers(String project_name,String user_name, String stateCode);

    ResponseEntity<String> updateProjectUser(String project_uid, int user_pn, int new_user_pn);

//    List<ProjectUser> selectProjectUsersData();

    ResponseEntity<String> deleteProjectUser(String project_uid, String user_name);
}
