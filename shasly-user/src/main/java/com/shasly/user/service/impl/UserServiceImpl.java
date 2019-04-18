package com.shasly.user.service.impl;

import com.shasly.common.bean.User;
import com.shasly.common.bean.UserActivate;
import com.shasly.common.bean.UserInfo;
import com.shasly.common.exception.UserException;
import com.shasly.common.utils.ActiveUtils;
import com.shasly.common.utils.EmailUtils;
import com.shasly.common.utils.MD5Utils;
import com.shasly.common.utils.TextUtils;
import com.shasly.user.mapper.AddressMapper;
import com.shasly.user.mapper.UserMapper;
import com.shasly.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserServiceImpl(UserMapper userMapper, AddressMapper addressMapper) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public User findUserByUId(int uid) {
        userMapper.findUserByUId(uid) ;
        return null;
    }

    /**
     * 登录
     */
    @Override
    public User login(String username, String password) throws UserException {
        User user = userMapper.findByUsername(username);
        String pass = MD5Utils.md5(user.getSalt() + password);
        if (user.getPassword().equals(pass)) {
            if (user.getStatus() == 0) {
                throw new UserException("用户还未激活");
            } else if (user.getStatus() == -1) {
                throw new UserException("用户已失效");
            } else {

                return user;
            }
        }
        return null;
    }

    /**
     * 注册
     *
     * @return
     */
    @Override
    public boolean registerByEmail(User user) {
        //密码加盐
        String salt = TextUtils.getString(8);
        //密码加密
        String password = MD5Utils.md5(salt + user.getPassword());
        user.setSalt(salt);
        user.setPassword(password);
        //设置用户为普通用户
        user.setRole(1);
        //设置用户未激活
        user.setStatus(0);
        //添加用户
        Integer uid = userMapper.insertUser(user);
        if (uid == null || uid.intValue() < 0) return false;

        //设置用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        //生成激活码
        String activation_code = ActiveUtils.activeCode();
        userInfo.setActivation_code(activation_code);
        //设置默认信息
        userInfo.setNickname(user.getUsername());
        userInfo.setNickname("http://bpic.588ku.com/element_origin_min_pic/19/03/07/e1ae437b6f6d004b1ee409bbb4196d05.jpg");
        //存入数据库
        int n = userMapper.insertUserInfo(userInfo);
        if (n <= 0) return false;
        //发送验证信息
        EmailUtils.sendEmail(new UserActivate(user.getUsername(), user.getEmail(), userInfo.getActivation_code()));

        return true;

    }

    @Override
    public boolean registerByPhone(User user) {
        //密码加盐
        String salt = TextUtils.getString(8);
        //密码加密
        String password = MD5Utils.md5(salt + user.getPassword());
        user.setSalt(salt);
        user.setPassword(password);
        //设置用户为普通用户
        user.setRole(1);
        //设置用户未激活
        user.setStatus(1);
        //添加用户
        userMapper.insertUser(user);
        Integer uid = user.getUid() ;

        if (uid == null || uid.intValue() < 0) return false;

        //分发一个购物车
        userMapper.addCartByUId(uid) ;
        //设置用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        //设置默认信息
        userInfo.setNickname(user.getUsername());
        userInfo.setProfile("http://bpic.588ku.com/element_origin_min_pic/19/03/07/e1ae437b6f6d004b1ee409bbb4196d05.jpg");
        //存入数据库
        int n = userMapper.insertUserInfo(userInfo);
        if (n <= 0)
            return false;
        else
            return true;
    }


    @Override
    public int remove(User user) {
        user.setStatus(-1);
        return userMapper.update(user);
    }

    @Override
    public int modify(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean checkUserName(String username) {
        User user = userMapper.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean activateUser(String username, String email, String activation_code) throws UserException {

        UserActivate userActivate = userMapper.findUserActivateByUsername(username) ;
        if (userActivate == null) throw new UserException("查无此用户") ;
        if (email.equals(userActivate.getEmail()) && activation_code.equals(userActivate.getActivation_code())){
            int len = userMapper.activateUser(username);
            if (len > 0) {
                return true;
            }
        }
        return false;

    }

    @Override
    public UserInfo findUserInfoByUId(int uid) {
        return userMapper.findUserInfoByUId(uid);
    }

    @Override
    public Integer findCartByBId(int uid) {
        Integer cid = userMapper.findCartByUId(uid);
        if (cid == null){
            throw new UserException("该用户没有分配购物车") ;
        }
        return cid ;
    }

}
