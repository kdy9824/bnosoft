package com.example.bno2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor 
// 매개변수가 없는 기본 생성자를 자동으로 생성
@AllArgsConstructor
// 클래스의 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
public class Excel {
    private int index;
    private String projectUid;
    private String projectName;
    private String startDt;
    private String endDt;
    private String startPredictDt;
    private String endPredictDt;
    private String projectStateCode;
    private String applyYn;
    private String projectDetail;

}

