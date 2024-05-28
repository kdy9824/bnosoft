package com.example.bno2.dto;

import lombok.Data;

@Data
public class EquipmentUser {

    private String equipClass;
    private String projectName;
    private String userName;
    private String modelName;
    private String serialNumber;
    private String specDesc;
    private int purchaseYear;
    private String manuCom;
    private String warrantyStartDt;
    private String warrantyEndDt;
    private String macAddress;
    private String StateCode;
    private String equipUid;
    private int userPn;
    private String lastUpdateDt;

}