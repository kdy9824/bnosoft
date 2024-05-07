package com.example.bno2.dao;

import lombok.Data;

@Data
public class EUP {

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
    private String equipmentStateCode;

}