package com.example.bno2.service;

import com.example.bno2.dto.Project;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    List<Project> selectProjectsByName(String projectName, String projectStateCode);

    ResponseEntity<String> insertProject(Project project, HttpSession session);

    ResponseEntity<String> updateProject(Project project, HttpSession session);

    ResponseEntity<String> deleteProject(String projectUid);

    ResponseEntity<String> updateUserStateForCompletedProject(String projectUid);
}