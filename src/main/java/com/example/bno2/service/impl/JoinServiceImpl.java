package com.example.bno2.service.impl;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.JoinMapper;
import com.example.bno2.service.JoinService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    JoinMapper joinMapper;

    @Override
    public String checkEmailDuplicate(String email) {
        if(joinMapper.checkEmailDuplicate(email) == 0)
            return "사용 가능한 이메일입니다.";
        else
            return "중복된 이메일은 사용할 수 없습니다.";
    }

    @Override
    public String addUser(HttpSession session, User user) {

        session.removeAttribute("createdAuthCode");

        if(joinMapper.addUser(user) > 0)
            return "BNOSOFT에 가입하신 것을 환영합니다.";
        else
            return "회원가입에 실패하였습니다.";

    }

}