package com.shasly.user.service;

import com.shasly.common.bean.User;
import com.shasly.common.exception.UserException;

import java.util.List;

public interface UserService {

    public List<User> findAll() ;
    public User findById(int id) ;
    public User login(String username, String password) throws UserException;

    public int remove(User user) ;
    public int modify(User user) ;

    public boolean registerByEmail(User user);
    public boolean registerByPhone(User user);

    public boolean checkUserName(String username);
    public boolean activateUser(String email, String create_time);
    User autoLogin(String username, String password);

}
