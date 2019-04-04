package com.shasly.order.service;

import com.shasly.common.bean.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(String token, List<Integer> ids, Integer aid);

    List<Order> getAllOrderList(String token);
}
