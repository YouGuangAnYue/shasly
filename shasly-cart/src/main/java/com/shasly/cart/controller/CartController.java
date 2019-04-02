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
import com.shasly.cart.service.impl.CartServiceImpl;
import com.shasly.common.bean.Cart;
import com.shasly.common.bean.CartList;
import com.shasly.common.bean.Goods;
import com.shasly.common.bean.User;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.DataListUtils;
import com.shasly.common.utils.TextUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController("/cart")
public class CartController {

    private final CartService cartService;
    private final JedisClientPool jedisClientPool ;

    //通过构造方法的方式自动注入
    public CartController(CartService cartService, JedisClientPool jedisClientPool) {
        this.cartService = cartService;
        this.jedisClientPool = jedisClientPool;
    }


    /**
     * 添加商品到购物车
     */
    @PostMapping(value = "/addcart")
    public void addCart(@RequestParam("goodsId") Integer gid, @RequestParam("number") Integer number) {


        // 取得商品价格
        Goods goods = new GoodsServiceImpl().findById(pid);
        if (goods == null) {
            request.setAttribute("mag", "查无此商品");
            request.getRequestDispatcher("/message.jsp");
            return;
        }
        double money = goods.getPrice();

        // 取得用户id
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();

        // 添加商品
        // 查看购物车内是否存在该商品
        Cart cart = cartService.findByIdAndPId(id, pid);
        if (cart == null) {
            cart = new Cart(id, pid, num, money);
            cartService.add(cart);
        } else {
            cart.setNum(cart.getNum() + num);
            cartService.update(cart);
        }

        // 重定向
        try {
            response.sendRedirect(request.getContextPath() + "/cartSuccess.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取用户购物车清单
     *
     * @param request
     * @param response
     */
    @GetMapping("/getcart")
    public void getCart(HttpServletRequest request, HttpServletResponse response) {

        CartList cartList = DataListUtils.getCartList(request, response);
        request.setAttribute("cart", cartList);
        try {
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mag", "服务器忙");
            request.getRequestDispatcher("/error/error.jsp");
        }

    }

    /**
     * 更改商品数量
     *
     * @param request
     * @param respon3se
     */
    @PostMapping("/addcartajax")
    public void addCartAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获取商品id已经改变数量
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("number");
        // System.out.println(goodsId + "..." + number);
        int pid = 0;
        int num = 0;
        if (!TextUtils.empty(goodsId)) {
            pid = Integer.parseInt(goodsId);
        }
        if (!TextUtils.empty(number)) {
            num = Integer.parseInt(number);
        }

        // 获取用户id
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();

        // 判断操作类型
        //System.out.println(num);
        if (num == 0) { // 删除
            cartService.removeByIdAndPId(id, pid);
        } else { // 修改数量
            Cart cart = cartService.findByIdAndPId(id, pid);
            cart.setNum(cart.getNum() + num);
            cartService.update(cart);
        }

    }

    /**
     * 清空购物车
     *
     * @param request
     */
    @GetMapping("clearcart")
    public void clearCartAjax(HttpServletRequest request) {
        // 获取用户id
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();

        CartService service = new CartServiceImpl();
        service.removeById(id);
    }
}
