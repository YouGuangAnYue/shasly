package com.shasly.user.mapper;

import com.shasly.common.bean.User;
import com.shasly.common.bean.UserActivate;
import com.shasly.common.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> findAll() ;
    User findUserByUId(int uid) ;
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password) ;
    //插入并获取用户id
    int insertUser(User user) ;
    //返回更新成功数
    int update(User user) ;
    User findByUsername(String username);
    int activateUser(String username);

    int insertUserInfo(UserInfo userInfo);

    UserActivate findUserActivateByUsername(String username);

    UserInfo findUserInfoByUId(int uid);

    int addCartByUId(Integer uid);

    Integer findCartByUId(int uid);
}
