package com.example.bno2.mapper;

import com.example.bno2.dto.ProjectUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectUserMapper {

    List<ProjectUser> selectProjectUsers(String projectName, String userName, String stateCode);

    int updateProjectUser(String projectUid, int userPn, int newUserPn);

//    List<ProjectUser> selectProjectUsersData();

    int deleteProjectUser(String projectUid, String userName);
}