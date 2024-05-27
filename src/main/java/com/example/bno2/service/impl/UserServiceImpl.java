package com.example.bno2.service.impl;

import com.example.bno2.dto.Code;
import com.example.bno2.dto.User;
import com.example.bno2.mapper.CodeMapper;
import com.example.bno2.mapper.UserMapper;
import com.example.bno2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CodeMapper codeMapper;

    private String replaceDeptText(String dept) {

        List<Code> codeList = codeMapper.getUserDeptCode();

        for(Code code : codeList){
            if(dept.equals(code.getDetailClassCode()))
                return code.getDetailDesc();
        }

        return dept;

    }

    private String replacePosText(String pos) {

        List<Code> codeList = codeMapper.getUserPosCode();

        for(Code code : codeList){
            if(pos.equals(code.getDetailClassCode()))
                return code.getDetailDesc();
        }

        return pos;

    }

    private String replaceStateText(String state) {

        List<Code> codeList = codeMapper.getUserStateCode();

        for(Code code : codeList){
            if(state.equals(code.getDetailClassCode()))
                return code.getDetailDesc();
        }

        return state;

    }

    private void replaceCodeToText(User user){

        user.setDeptCode(replaceDeptText(user.getDeptCode()));
        user.setPosCode(replacePosText(user.getPosCode()));
        user.setStateCode(replaceStateText(user.getStateCode()));

    }

    @Override
    public List<User> selectUsersByName(String name, String dept) {
        List<User> userList = userMapper.selectUsersByNameDept(name, dept);
//        for (User user : userList)
//            replaceCodeToText(user);

        return userList;

    }

    @Override
    public ResponseEntity<String> addUser(User user, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("loginUserPn", loginUser.getPn());

        if (userMapper.insertUser(params) > 0)
            return new ResponseEntity<>("User inserted successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to insert user", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateUser(User user, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("loginUserPn", loginUser.getPn());

        if (userMapper.updateUser(params) > 0)
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @Override
    public ResponseEntity<String> updateUserState(String stateCode, int pn) {

        if (userMapper.updateUserState(stateCode ,pn) > 0)
            return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}