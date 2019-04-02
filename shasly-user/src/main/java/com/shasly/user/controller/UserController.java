package com.shasly.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.bean.User;
import com.shasly.common.exception.UserException;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.TextUtils;
import com.shasly.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class UserController {

    private final UserService userService ;
    private final DefaultKaptcha defaultKaptcha ;
    private final JedisClientPool jedisClientPool ;

    public UserController(UserService userService, DefaultKaptcha defaultKaptcha, JedisClientPool jedisClientPool) {
        this.userService = userService;
        this.defaultKaptcha = defaultKaptcha;
        this.jedisClientPool = jedisClientPool;
    }

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        @RequestParam("vcode") String vcode, @RequestParam("auto") String auto) {

        // 用户名密码不能为空
        if (TextUtils.empty(username) || TextUtils.empty(password)) {
            return "login.jsp";
        }
        // 验证码不能为空
        if (TextUtils.empty(vcode)) {
            request.setAttribute("msg", "验证码不能为空");
            return "/login.jsp";
        }
        // 查询用户是否存在
        UserService us = new UserServiceImpl();
        User user = us.login(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (auto != null) {
                username = URLEncoder.encode(username, "utf-8") ;
                Cookie cookie = new Cookie("userInfo", username + "#" + MD5Utils.md5(password));
                cookie.setMaxAge(14 * 24 * 60 * 60);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
        } else {
            request.setAttribute("msg", "密码错误");
            return "/login.jsp";
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return null;
    }

    /**
     * 注册
     *
     * @param request
     * @param response
     */
    public void register(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            // System.out.println(user);
            UserService us = new UserServiceImpl();
            us.register(user);
            // 跳转到注册成功页面
            response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
        } catch (Exception e) {
            try {
                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping(value = "/checkusername")
    public ResultBean checkUserName(@RequestParam("username") String username) {

        boolean b = userService.checkUserName(username);
        if (b){
            return new ResultBean(false,"用户名已存在",null) ;
        }

        return new ResultBean(true,"用户名可用",null) ;

    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/vcode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //获取vcde标记
        Cookie[] cookies = request.getCookies();
        String vcode = null ;
        for (Cookie coo : cookies) {
            if ("vcode".equals(coo.getName())){
                vcode = coo.getValue() ;
            }
        }

        // 生成验证码
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            if (vcode == null){
                vcode = TextUtils.getString(64) ;
                Cookie cookie = new Cookie("vcode",vcode) ;
                cookie.setMaxAge(5 * 60) ;
                response.addCookie(cookie);
            }
            jedisClientPool.set(vcode,createText) ;
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }

    /**
     * 检验验证码
     * @param code
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/checkcode/{code}")
    public ResultBean checkCode(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {
        //获取vcde标记
        Cookie[] cookies = request.getCookies();
        String vcode = null ;
        for (Cookie coo : cookies) {
            if ("vcode".equals(coo.getName())){
                vcode = coo.getValue() ;
            }
        }

        if (vcode == null){
            return new ResultBean(false,"验证码已过期",null) ;
        }
        String rightCode = jedisClientPool.get(vcode);
        if (rightCode.equalsIgnoreCase(code)) {
            return new ResultBean(true,"验证通过",null) ;
        } else {
            return new ResultBean(false,"验证码错误",null) ;
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getSession().getAttribute("user") != null) {
            request.getSession().removeAttribute("user");
        }

        Cookie cookie = new Cookie("userInfo", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/login.jsp");

    }

    /**
     * 添加地址
     *
     * @param request
     * @param response
     */
    public void addAddress(HttpServletRequest request, HttpServletResponse response) {


        // 获取地址信息
        Address address = new Address();
        try {
            BeanUtils.populate(address, request.getParameterMap());
            System.out.println(address);
        } catch (Exception e) {
            e.printStackTrace();
        }

        service.add(address);

        getAddress(request, response);
    }

    /**
     * 获取用户全部地址
     *
     * @param request
     * @param response
     */
    public void getAddress(HttpServletRequest request, HttpServletResponse response) {
        // 查看地址
        List<Address> addList = DataListUtils.getAddressList(request, response);

        request.setAttribute("addList", addList);
        // 转发到地址管理界面
        try {
            request.getRequestDispatcher("/self_info.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改为默认地址
     *
     * @param request
     * @param response
     */
    public void defaultAddress(HttpServletRequest request, HttpServletResponse response) {
        AddressService service = new AddressServiceImpl();
        // 获取地址ID
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);
        // 取得所有地址
        List<Address> addList = DataListUtils.getAddressList(request, response);
        for (Address address : addList) {
            if (address.getId() == id) {
                address.setLevel(1);
            } else {
                address.setLevel(0);
            }
            // 修改地址属性
            service.update(address);
        }
        getAddress(request, response);
    }

    /**
     * 删除地址
     *
     * @param request
     * @param response
     */
    public void deleteAddress(HttpServletRequest request, HttpServletResponse response) {
        AddressService service = new AddressServiceImpl();
        // 获取地址ID
        String _id = request.getParameter("id");
        int id = Integer.parseInt(_id);

        // 输出地址
        service.remove(id);

        getAddress(request, response);
    }

    /**
     * 修改地址
     *
     * @param request
     * @param response
     */
    public void updateAddress(HttpServletRequest request, HttpServletResponse response) {
        AddressService service = new AddressServiceImpl();

        //获取前台数据
        Address address = new Address() ;
        try {
            BeanUtils.populate(address, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取用户id
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getId();

        //修改地址
        address.setUid(uid);
        service.update(address) ;

        getAddress(request, response);

    }



}
