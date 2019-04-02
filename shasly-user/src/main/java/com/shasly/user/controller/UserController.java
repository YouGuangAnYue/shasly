package com.shasly.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.bean.User;
import com.shasly.common.exception.UserException;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.TextUtils;
import com.shasly.user.service.UserService;
import com.shasly.user.vo.LoginVo;
import com.shasly.user.vo.RegisterVo;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

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

    private final UserService userService;
    private final DefaultKaptcha defaultKaptcha;
    private final JedisClientPool jedisClientPool;

    public UserController(UserService userService, DefaultKaptcha defaultKaptcha, JedisClientPool jedisClientPool) {
        this.userService = userService;
        this.defaultKaptcha = defaultKaptcha;
        this.jedisClientPool = jedisClientPool;
    }

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public ResultBean login(@RequestBody LoginVo loginVo, @CookieValue(value = "vcode") String vcode, HttpServletResponse response) {

        // 用户名密码不能为空
        if (TextUtils.empty(loginVo.getUsername()) || TextUtils.empty(loginVo.getPassword())) {
            return new ResultBean(false, "用户名或密码为空", null);
        }
        // 检验验证码
        if (!jedisClientPool.get(vcode).equals(loginVo.getVcode())) {
            return new ResultBean(false, "验证码错误", null);
        }
        // 查询用户是否存在
        User user = null;
        try {
            user = userService.login(loginVo.getUsername(), loginVo.getPassword());
        } catch (UserException e) {
            return new ResultBean(false, "服务器忙...", null);
        }
        if (user != null) {
            String autoLogin = TextUtils.getString(64);
            Cookie cookie = new Cookie("autoLogin", autoLogin);
            
            jedisClientPool.set(autoLogin, "" + user.getUid(), "nx", "ex", (long) (30 * 60)) ;
            if (loginVo.getAuto() != null) {
                cookie.setMaxAge(14 * 24 * 60 * 60);
            }else {
                cookie.setMaxAge(30 * 60);
            }
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResultBean(true,"登录成功",loginVo.getUsername()) ;
        } 
        return new ResultBean(false,"用户名或密码错误",null) ;
        
    }

    /**
     * 注册
     */
    @PostMapping(value = "/register")
    public ResultBean register(@RequestBody RegisterVo registerVo) {
        if (!registerVo.getPassword().equals(registerVo.getRePassword())) {
            return new ResultBean(false, "两次密码不一致", null);
        }
        String email = registerVo.getEmail();
        String phone = registerVo.getPhone();
        boolean b = false;
        if (registerVo.getEmail() == null) {
            if (!registerVo.getPhoneCode().equals(jedisClientPool.get(phone))) {
                return new ResultBean(false, "验证码错误", null);
            }
            b = userService.registerByPhone(new User(registerVo.getUsername(), registerVo.getPassword(), null, phone));
        } else {
            b = userService.registerByEmail(new User(registerVo.getUsername(), registerVo.getPassword(), email, null));
        }
        if (b)
            return new ResultBean(true, "注册成功", null);
        else
            return new ResultBean(false, "注册失败", null);
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping(value = "/checkusername")
    public ResultBean checkUserName(@RequestParam("username") String username) {

        boolean b = userService.checkUserName(username);
        if (b) {
            return new ResultBean(false, "用户名已存在", null);
        }

        return new ResultBean(true, "用户名可用", null);

    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/vcode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取vcde标记
        Cookie[] cookies = request.getCookies();
        String vcode = null;
        for (Cookie coo : cookies) {
            if ("vcode".equals(coo.getName())) {
                vcode = coo.getValue();
            }
        }

        // 生成验证码
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生成验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            if (vcode == null) {
                vcode = TextUtils.getString(64);
                Cookie cookie = new Cookie("vcode", vcode);
                //保存5分钟
                cookie.setMaxAge(5 * 60);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
            //写入redis缓存当中
            jedisClientPool.set(vcode, createText, "nx", "ex", (long) (5 * 60));
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
     *
     * @param code
     * @param vcode
     * @param response
     * @return
     */
    @GetMapping(value = "/checkcode/{code}")
    public ResultBean checkCode(@PathVariable("code") String code, @CookieValue(value = "vcode") String vcode, HttpServletResponse response) {
        //判断vcode标记
        if (vcode == null) {
            return new ResultBean(false, "验证码已过期", null);
        }
        String rightCode = jedisClientPool.get(vcode);
        if (rightCode.equalsIgnoreCase(code)) {
            return new ResultBean(true, "验证通过", null);
        } else {
            return new ResultBean(false, "验证码错误", null);
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
        Address address = new Address();
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
        service.update(address);

        getAddress(request, response);

    }


}
