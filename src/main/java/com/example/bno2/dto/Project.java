package com.example.bno2.dto;

import lombok.Data;

@Data
public class Project {

    private String projectUid;
    private String projectName;
    private String startDt;
    private String endDt;
    private String startPredictDt;
    private String endPredictDt;
    private String stateCode;
    private String applyYn;
    private String projectDetail;

}