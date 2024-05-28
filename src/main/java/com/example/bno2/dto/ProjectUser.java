package com.example.bno2.dto;

import lombok.Data;

@Data
public class ProjectUser {

    private String projectUid;
    private String projectName;
    private String projectStateCode;
    private int userPn;
    private String userName;
    private String role;
    private String roleDetail;
    private String projectDetail;
    private String lastUpdateDt;

}