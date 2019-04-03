package com.shasly.user.service;

import com.shasly.common.bean.User;
import com.shasly.common.exception.UserException;

import java.util.List;

public interface UserService {

    User findUserByUId(int uid) ;
    User login(String username, String password) throws UserException;
    int remove(User user) ;
    int modify(User user) ;
    boolean registerByEmail(User user);
    boolean registerByPhone(User user);

    boolean checkUserName(String username);
    boolean activateUser(String username, String email, String activation_code);

}
