package com.example.bno2.dto;

import lombok.Data;

@Data
public class ProjectUser {

    private String projectUid;
    private String projectName;
    private String stateCode;
    private int userPn;
    private String userName;
    private int newUserPn;
    private String role;
    private String roleDetail;
    private String projectDetail;

}