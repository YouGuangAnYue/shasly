package com.shasly.order.mapper;

import com.shasly.common.bean.CartDetail;
import com.shasly.common.bean.CartList;
import com.shasly.common.bean.Order;
import com.shasly.common.bean.OrderDetail;

import java.util.List;

public interface OrderMapper {

    List<CartList> findCartDetailByIds(List<Integer> ids);

    int createOrder(Order order);

    int createOrderDetail(OrderDetail orderDetail);
}
