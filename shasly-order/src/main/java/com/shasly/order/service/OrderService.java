package com.shasly.order.service;

import com.shasly.common.bean.CartList;
import com.shasly.common.bean.Order;
import com.shasly.common.bean.OrderDetailList;

import java.util.List;

public interface OrderService {

    Order createOrder(String token, List<Integer> ids, Integer aid);

    List<Order> getAllOrderList(String token);

    List<Order> getOrderListByStatus(String token, Integer status);

    List<CartList> verifyOrder(String token, List<Integer> ids);

    boolean updateOrderStruts(String token, String oid, Integer status);
}
