package com.shasly.order.controller;

import com.shasly.common.bean.Order;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.bean.User;
import com.shasly.common.utils.PageBeanUtils;
import com.shasly.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 提交用户订单
     * @param token
     * @param ids 购物车详情id集合
     * @param aid 地址id
     * @return
     */
    @CrossOrigin
    @PostMapping("/submitorder")
    public ResultBean submitOrder(@CookieValue(value = "token") String token,
                                  @RequestParam(value = "ids") List<Integer> ids,
                                  @RequestParam(value = "aid") Integer aid) {
        Order order = orderService.createOrder(token, ids, aid);
        if (order != null)
            return new ResultBean(true, "提交订单成功", order);
        else
            return new ResultBean(false, "提交订单失败", null);
    }

    /**
     * 查询该用户的所有订单
     * @param token
     * @param pageSize
     * @param pageNum
     * @return
     */
    @CrossOrigin
    @GetMapping("/getorderlist/{pageSize}/{pageNum}")
    public ResultBean getOrderList(@CookieValue(value = "token") String token,
                                   @PathVariable(value = "pageSize") Integer pageSize,
                                   @PathVariable(value = "pageNum") Integer pageNum) {
        List<Order> orderList = orderService.getAllOrderList(token) ;
        if (pageNum == null) pageNum = 1 ;
        ResultBean resultBean = PageBeanUtils.baseResultBean(orderList, pageNum, pageSize);
        return resultBean ;
    }


    /**
     * 查询不同状态订单的接口
     * @param status
     * @return
     */
    @CrossOrigin
    @GetMapping("/getorderbystatus/{status}/{pageSize}/{pageNum}")
    public ResultBean getOrderStatus(@CookieValue String token,
                                     @PathVariable(value = "status") Integer status,
                                     @PathVariable("pageSize") Integer pageSize,
                                     @PathVariable("pageNum") Integer pageNum) {
        List<Order> orderList = orderService.getOrderListByStatus(token,status) ;
        if (pageNum == null) pageNum = 1 ;
        ResultBean resultBean = PageBeanUtils.baseResultBean(orderList, pageNum, pageSize);
        return resultBean ;
    }


    /**
     * 更新订单状态
     *
     * @param uid
     * @param status
     * @param oid
     * @return
     */
    @CrossOrigin
    @RequestMapping("/changeOrderStatus")
    public ResultBean changeOrderStatus(int uid, int status, String oid) {

        return null ;
    }


    /**
     * 退款接口
     * @param gid
     * @param oid
     * @param status
     * @return
     */
    @CrossOrigin
    @RequestMapping("returns")
    @ResponseBody
    public ResultBean returns(int gid, String oid, int status) {

        return null ;
    }


    /**
     * 查询用户所有的退货记录
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping("/getReturns")
    @ResponseBody
    public ResultBean getReturns() {
        return null ;
    }

}
