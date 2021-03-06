package com.shasly.cart.controller;/*
 *________________********_______________________
 *______________************_____________________
 *______________*************____________________
 *_____________**__***********___________________
 *____________***__******_*****__________________
 *____________***_*******___****_________________
 *___________***__**********_****________________
 *__________****__***********_****_______________
 *________*****___***********__*****_____________
 *_______******___***_********___*****___________
 *_______*****___***___********___******_________
 *______******___***__***********___******_______
 *_____******___****_**************__******______
 *____*******__***** ***************_ ******_____
 *____*******__***** ****************_ ******____
 *___*******__******_*****************_*******___
 *___*******__******_******_*********___******___
 *___*******____**__******___******_____******___
 *___*******________******____*****_____*****____
 *____******________*****_____*****_____****_____
 *_____*****________****______*****_____***______
 *______*****______;***________***______*________
 *________**_______****________****______________
 *
 *          初闻天籁之音,未使心之将来——初音未来
 *
 */

import com.shasly.cart.service.CartService;
import com.shasly.common.bean.*;
import com.shasly.common.utils.PageBeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class CartController {

    private final CartService cartService;

    //通过构造方法的方式自动注入
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    /**
     * 添加商品到购物车
     *
     * @param cartDetail (gid,number,param)
     * @param token
     */
    @PostMapping(value = "/addcart")
    public ResultBean addCart(@RequestBody() CartDetail cartDetail,
                              @CookieValue(value = "token", required = false) String token) {

        if (token == null) return new ResultBean(false, "登录失效", null);
        try {
            boolean b = cartService.insertCartDetail(token, cartDetail);
            if (b) return new ResultBean(true, "添加商品到购物车成功", null);
        } catch (Exception e) {
            return new ResultBean(false, "服务器繁忙", e.getMessage());
        }
        return new ResultBean(false, "添加商品到购物车失败", null);

    }


    /**
     * 获取用户购物车清单
     *
     * @param token
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/getcart/{pageSize}/{pageNum}")
    public ResultBean getCart(@CookieValue(value = "token", required = false) String token,
                              @PathVariable(value = "pageSize") Integer pageSize,
                              @PathVariable(value = "pageNum") Integer pageNum) {

        if (token == null) return new ResultBean(false, "登录失效", null);
        List<CartList> lists = null;
        try {
            lists = cartService.findCartListByToken(token, pageSize, pageNum);
            return PageBeanUtils.baseResultBean(lists, pageNum, pageSize);
        } catch (Exception e) {
            return new ResultBean(false, "服务器繁忙", e.getMessage());
        }
    }

    /**
     * 更改商品数量
     *
     * @param token
     * @param gid
     * @param number
     * @return
     */
    @GetMapping("/changecartdetail/{gid}/{number}")
    public ResultBean changeCartDetail(@CookieValue(value = "token", required = false) String token,
                                       @PathVariable(value = "gid") Integer gid,
                                       @PathVariable(value = "number") Integer number) {

        if (token == null) return new ResultBean(false, "登录失效", null);
        try {
            boolean b = cartService.updateCartDetailNumber(token, gid, number);
            if (b) return new ResultBean(true, "修改数量成功", null);
        } catch (Exception e) {
            return new ResultBean(false, "服务器繁忙", null);
        }

        return new ResultBean(false, "修改数量失败", null);
    }

    /**
     * 清空购物车
     *
     * @param token
     * @return
     */
    @GetMapping("/clearcart")
    public ResultBean clearCart(@CookieValue(value = "token", required = false) String token) {

        if (token == null) return new ResultBean(false, "登录失效", null);
        try {
            boolean b = cartService.clearCart(token);
            if (b) return new ResultBean(true, "清空购物车成功", null);
        } catch (Exception e) {
            return new ResultBean(false, "服务器繁忙", e.getMessage());
        }

        return new ResultBean(false, "清空购物车失败", null);
    }

}
