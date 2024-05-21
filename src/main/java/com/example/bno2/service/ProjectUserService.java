package com.example.bno2.service;

import com.example.bno2.dto.ProjectUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProjectUserService {

    List<ProjectUser> selectProjectUsers(String projectName, String userName, String stateCode);

    ResponseEntity<String> updateProjectUser(String projectUid, int userPn, int newUserPn);

//    List<ProjectUser> selectProjectUsersData();

    ResponseEntity<String> deleteProjectUser(String projectUid, String userName);
}
