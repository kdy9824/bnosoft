package com.example.bno2.dto;

import lombok.Data;

@Data
public class Excel {
    private int index;
    private String porjectUid;
    private String projectName;
    private String startDt;
    private String endDt;
    private String startPredictDt;
    private String endPredictDt;
    private String projectStateCode;
    private String applyYn;
    private String projectDetail;
}
