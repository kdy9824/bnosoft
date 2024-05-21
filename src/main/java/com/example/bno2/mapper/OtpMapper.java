package com.example.bno2.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OtpMapper {

    int checkSecretKey(int userPn);

    int addSecretKey(int userPn, String secretKey);

    String getSecretKey(int userPn);

    int checkQrBlob(int userPn);

    int addQrBlob(int userPn, byte[] qrBlob);

    int otpIsRegistered(int userPn);

    int updateOtpIsRegistered(int userPn);

}