package com.example.bno2.mapper;

import com.example.bno2.dto.ProjectUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectUserMapper {

    List<ProjectUser> selectProjectUsers(String project_name,String user_name, String stateCode);

    int updateProjectUser(String project_uid, int user_pn, int new_user_pn);

//    List<ProjectUser> selectProjectUsersData();

    int deleteProjectUser(String project_uid, String user_name);
}