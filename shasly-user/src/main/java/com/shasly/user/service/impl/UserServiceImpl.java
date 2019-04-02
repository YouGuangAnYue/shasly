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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public UserServiceImpl(UserMapper userMapper, AddressMapper addressMapper) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    /**
     * 登录
     */
    @Override
    public User login(String username, String password) throws Exception{
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
        return null ;
    }

    /**
     * 自动登录
     */
    @Override
    public User autoLogin(String username, String password) {

        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user.getStatus() == 0) {
            throw new UserException("用户还未激活");
        } else if (user.getStatus() == -1) {
            throw new UserException("用户已失效");
        } else {
            return user;
        }

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
        //存入数据库
        int n = userMapper.insertUserInfo(userInfo);
        if (n <= 0) return false;
        //发送验证信息
        EmailUtils.sendEmail(new UserActivate(user.getUsername(), user.getEmail(), userInfo.getActivation_code()));

        return true;

    }

    @Override
    public boolean registerByPhone(User user) {
        return false;
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
    public boolean activateUser(String email, String create_time) {

        int len = userMapper.activateUser(email, create_time);
        if (len > 0) {
            return true;
        }
        return false;

    }

    /**
     * 根据Cookie名，获取内容
     *
     * @param name    cookie名称
     * @param request http请求
     * @return 内容
     */
    private String getCookiesValue(String name, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String res = null;
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                res = cookie.getValue();
            }
        }
        return res;
    }

}
