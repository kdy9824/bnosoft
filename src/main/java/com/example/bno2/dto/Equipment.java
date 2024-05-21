package com.example.bno2.dto;

import lombok.Data;

@Data
public class Equipment {

    private String equipUid;
    private String equipClass;
    private String serialNumber;
    private String modelName;
    private String specDesc;
    private String warrantyStartDt;
    private String warrantyEndDt;
    private String manuCom;
    private String macAddress;
    private String stateCode;
    private int purchaseYear;
    private int userPn;
    private String userName;
    private String applyYN;
    private String email;

}