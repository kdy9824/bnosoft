package com.example.bno2.service;

import com.example.bno2.dto.Equipment;
import com.example.bno2.dto.ProjectUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProjectUserService {

    List<ProjectUser> selectProjectUsers(String projectName, String userName, String projectStateCode);

    ResponseEntity<String> insertProjectTeam(String projectUid, int userPn, String role, String roleDetail);

    ResponseEntity<String> updateProjectUser(String projectUid, int userPn, String role, String roleDetail);

//    List<ProjectUser> selectProjectUsersData();

    ResponseEntity<String> deleteProjectUser(String projectUid, int userPn);

    ResponseEntity<String> existsInProject(String projectUid,int userPn);
}
