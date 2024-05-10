package com.example.bno2.service;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.OtpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    private OtpMapper otpMapper;

    public int checkSecretKey(int userPn){
        return otpMapper.checkSecretKey(userPn);
    }

    public int addSecretKey(int userPn, String secretKey){
        return otpMapper.addSecretKey(userPn, secretKey);
    }

    public String getSecretKey(int userPn){
        return otpMapper.getSecretKey(userPn);
    }

    public int checkQrBlob(int userPn){
        return otpMapper.checkQrBlob(userPn);
    }

    public int addQrBlob(int userPn, byte[] qrBlob){
        return otpMapper.addQrBlob(userPn, qrBlob);
    }

    public int otpIsRegistered(int userPn){
        return otpMapper.otpIsRegistered(userPn);
    }

    public int updateOtpIsRegisterd(int userPn){
        return otpMapper.updateOtpIsRegistered(userPn);
    }

}