package com.shasly.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shasly.common.bean.Address;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.bean.User;
import com.shasly.common.bean.UserInfo;
import com.shasly.common.exception.UserException;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.properties.CookieProperties;
import com.shasly.common.utils.TextUtils;
import com.shasly.user.service.AddressService;
import com.shasly.user.service.UserService;
import com.shasly.user.vo.LoginVo;
import com.shasly.user.vo.RegisterVo;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*",maxAge = 3600,allowCredentials="true")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;
    private final DefaultKaptcha defaultKaptcha;
    private final JedisClientPool jedisClientPool;

    public UserController(UserService userService, AddressService addressService, DefaultKaptcha defaultKaptcha, JedisClientPool jedisClientPool) {
        this.userService = userService;
        this.addressService = addressService;
        this.defaultKaptcha = defaultKaptcha;
        this.jedisClientPool = jedisClientPool;
    }

    /**
     * 登录
     *
     * @param loginVo
     * @param vcode
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    public ResultBean login(@RequestBody LoginVo loginVo, @CookieValue(value = "vcode", required = false) String vcode, HttpServletResponse response) {

        // 用户名密码不能为空
        if (TextUtils.empty(loginVo.getUsername()) || TextUtils.empty(loginVo.getPassword())) {
            return new ResultBean(false, "用户名或密码为空", null);
        }
        //验证码不能为空
        if (vcode == null) return new ResultBean(false,"验证码已过期",null) ;
        // 检验验证码
        if (vcode == null || !jedisClientPool.get(vcode).equals(loginVo.getVcode())) {
            return new ResultBean(false, "验证码错误", null);
        }
        // 查询用户是否存在
        User user = null;
        Integer cid = null ;
        UserInfo userInfo = null ;
        try {
            user = userService.login(loginVo.getUsername(), loginVo.getPassword());
            if (user != null) {
                cid = userService.findCartByBId(user.getUid());
                userInfo = userService.findUserInfoByUId(user.getUid());
            }else {
                return new ResultBean(false, "用户名或密码错误", null);
            }
        } catch (UserException e) {
            return new ResultBean(false, e.getMessage(), null);
        }

        String token = TextUtils.getString(64);
        Cookie cookie = new Cookie("token", token);
        cookie.setDomain(CookieProperties.DOMAIN_PATTERN);
        cookie.setPath(CookieProperties.PATH);
        int time = 0;
        if (loginVo.getAuto() != null) {
            time = CookieProperties.MAX_AGE_TIME_TOKEN_AUTO ;
        } else {
            time = CookieProperties.MAX_AGE_TIME_TOKEN ;
        }
        cookie.setMaxAge(time);
        jedisClientPool.setex(token, "" + user.getUid(), time);
        jedisClientPool.setex(user.getUid() + "cart","" + cid , time);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return new ResultBean(true, "登录成功", TextUtils.empty(userInfo.getNickname()) ? loginVo.getUsername() : userInfo.getNickname());

    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping(value = "/register")
    public ResultBean register(@RequestBody RegisterVo registerVo) {
        if (!registerVo.getPassword().equals(registerVo.getRePassword())) {
            return new ResultBean(false, "两次密码不一致", null);
        }
        String email = registerVo.getEmail();
        String phone = registerVo.getPhone();
        boolean b = false;
//        if (registerVo.getEmail() == null) {
        String phoneCode = jedisClientPool.get(phone);
        if (phoneCode == null) return new ResultBean(false, "验证码已失效", null);
        if (!registerVo.getPhoneCode().equals(phoneCode)) {
            return new ResultBean(false, "验证码错误", null);
        }
        b = userService.registerByPhone(new User(registerVo.getUsername(), registerVo.getPassword(), null, phone));
        /*} else {
            b = userService.registerByEmail(new User(registerVo.getUsername(), registerVo.getPassword(), email, null));
        }*/
        if (b)
            return new ResultBean(true, "注册成功", null);
        else
            return new ResultBean(false, "注册失败", null);
    }

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/checkusername/{username}")
    public ResultBean checkUserName(@PathVariable("username") String username) {

        boolean b = userService.checkUserName(username);
        if (b) {
            return new ResultBean(false, "用户名已存在", null);
        }

        return new ResultBean(true, "用户名可用", null);

    }

    /**
     * 生成图片验证码
     *
     * @param request
     * @param response
     */
    @GetMapping(value = "/vcode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取vcde标记
        Cookie[] cookies = request.getCookies();
        String vcode = null;
        if (cookies != null) {
            for (Cookie coo : cookies) {
                if ("vcode".equals(coo.getName())) {
                    vcode = coo.getValue();
                }
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
                //保存10分钟
                cookie.setMaxAge(CookieProperties.MAX_AGE_TIME_VCODE);
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
            //写入redis缓存当中
            jedisClientPool.setex(vcode, createText, 10 * 60);
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
     * @return
     */
    @GetMapping(value = "/checkcode/{code}")
    public ResultBean checkCode(@CookieValue(value = "vcode", required = false) String vcode,
                                @PathVariable("code") String code) {

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
     */
    @GetMapping(value = "/logout")
    public ResultBean logOut(@CookieValue(value = "token", required = false) String token, HttpServletResponse response) {

        if (token != null) {
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            jedisClientPool.del(token);
        }

        return new ResultBean(true, "退出成功", null);

    }

    /**
     * 添加地址
     */
    @PostMapping(value = "/addaddress")
    public ResultBean addAddress(@RequestBody Address address, @CookieValue(value = "token", required = false) String token) {
        try {
            String uid = jedisClientPool.get(token);
            address.setUid(Integer.parseInt(uid));
            addressService.add(address);
            List<Address> list = addressService.findAddressByUId(uid);
            return new ResultBean(true, "添加成功", list);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(false, "服务器繁忙",e.getMessage()) ;
        }

    }

    /**
     * 获取用户全部地址
     */
    @GetMapping(value = "/getaddress")
    public ResultBean getAddress(@CookieValue(value = "token", required = false) String token) {
        // 查看地址
        try {
            String uid = jedisClientPool.get(token);
            List<Address> list = addressService.findAddressByUId(uid);
            return new ResultBean(true, "查询成功", list);
        } catch (Exception e) {
            return new ResultBean(false, "服务器忙", e.getMessage());
        }
    }

    /**
     * 修改为默认地址
     */
    @GetMapping(value = "/defaultAddress/{aid}")
    public ResultBean defaultAddress(@PathVariable(value = "aid") Integer aid, @CookieValue(value = "token", required = false) String token) {
        String uid = jedisClientPool.get(token);
        if (uid == null) return new ResultBean(false, "登录失效", null);
        // 取得所有地址
        List<Address> addList = addressService.findAddressByUId(uid);
        if (addList == null) return new ResultBean(false, "您还没有地址", null);
        try{
            for (Address address : addList) {
                if (address.getAid() == aid) {
                    address.setDef(1);
                } else if (address.getDef().intValue() == 1) {
                    address.setDef(0);
                } else {
                    continue;
                }
                // 修改地址属性
                addressService.update(address);
            }
            return new ResultBean(true, "设置成功", addList);
        }catch (Exception e){
            return new ResultBean(false, "设置失败", null);
        }
    }

    /**
     * 删除地址
     */
    @GetMapping(value = "/deleteaddress/{aid}")
    public ResultBean deleteAddress(@PathVariable(value = "aid") Integer aid, @CookieValue(value = "token", required = false) String token) {
        String uid = jedisClientPool.get(token);
        boolean b = addressService.deleteByUIdAndAId(aid, uid);
        if (b)
            return new ResultBean(true, "删除成功", null);
        else
            return new ResultBean(false, "删除失败", null);
    }

    /**
     * 修改地址
     */
    @PostMapping(value = "/updateaddress")
    public ResultBean updateAddress(@CookieValue(value = "token", required = false) String token, @RequestBody Address address) {
        String uid = jedisClientPool.get(token);
        address.setUid(Integer.parseInt(uid));
        boolean b = addressService.update(address);
        if (b)
            return new ResultBean(true, "修改成功", null);
        else
            return new ResultBean(false, "修改失败", null);
    }


}
