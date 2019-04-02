package com.shasly.user.mapper;

import com.shasly.common.bean.User;
import com.shasly.common.bean.UserInfo;

import java.util.List;

public interface UserMapper {

    public List<User> findAll() ;
    public User findById(int id) ;
    public User findByUsernameAndPassword(String username, String password) ;
    //返回用户id
    public int insertUser(User user) ;
    //返回更新成功数
    public int update(User user) ;
    public User findByUsername(String username);
    public int activateUser(String email, String code);

    int insertUserInfo(UserInfo userInfo);
}
