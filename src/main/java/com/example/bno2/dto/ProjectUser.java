package com.example.bno2.dto;

import lombok.Data;

@Data
public class ProjectUser {

    private String project_uid;
    private String project_name;
    private String stateCode;
    private int user_pn;
    private String user_name;
    private int new_user_pn;
    private String project_detail;
    private String role;
    private String role_detail;

}