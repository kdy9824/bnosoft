package com.example.bno2.service.impl;

import com.example.bno2.dto.User;
import com.example.bno2.mapper.JoinMapper;
import com.example.bno2.service.JoinService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService {

    @Autowired
    JoinMapper joinMapper;

    @Override
    public ResponseEntity<String> checkEmailDuplicate(String email) {

        if(joinMapper.checkEmailDuplicate(email) == 0)
            return new ResponseEntity<>("사용 가능한 이메일입니다.", HttpStatus.OK);
        else
            return new ResponseEntity<>("중복된 이메일은 사용할 수 없습니다.", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> addUser(HttpSession session, User user) {

        session.removeAttribute("createdAuthCode");

        if(joinMapper.addUser(user) > 0)
            return new ResponseEntity<>("BNOSOFT에 가입하신 것을 환영합니다.",HttpStatus.CREATED);
        else
            return new ResponseEntity<>("회원가입에 실패하였습니다.",HttpStatus.OK);

    }

}