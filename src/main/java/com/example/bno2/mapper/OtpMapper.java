package com.example.bno2.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OtpMapper {

    int checkSecretKey(int userPn);

    int addSecretKey(int userPn, String secretKey);

    String getSecretKey(int userPn);

    int checkQrBase64(int userPn);

    int addQrBase64(int userPn, String qrBase64);

    String getQrBase64(int userPn);

    int otpIsRegistered(int userPn);

    int updateOtpIsRegistered(int userPn);

}