package com.shasly.order.service.impl;

import com.shasly.common.bean.CartDetail;
import com.shasly.common.bean.CartList;
import com.shasly.common.bean.Order;
import com.shasly.common.bean.OrderDetail;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.TextUtils;
import com.shasly.order.mapper.OrderMapper;
import com.shasly.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper ;
    private final JedisClientPool jedisClientPool ;

    public OrderServiceImpl(OrderMapper orderMapper, JedisClientPool jedisClientPool) {
        this.orderMapper = orderMapper;
        this.jedisClientPool = jedisClientPool;
    }

    @Override
    public Order createOrder(String token, List<Integer> ids, Integer aid) {
        String uid = jedisClientPool.get(token);
        List<CartList> cartLists = orderMapper.findCartDetailByIds(ids) ;
        Order order = new Order() ;
        String oid = TextUtils.getString(32) ;
        BigDecimal total_price = new BigDecimal(0) ;
        for (CartList cartList : cartLists) {
            total_price = total_price.add(cartList.getGoods().getPrice()) ;
        }
        order.setOid(oid);
        order.setUid(Integer.parseInt(uid));
        order.setTotal_price(total_price);
        orderMapper.createOrder(order) ;
        for (CartList c : cartLists) {
            OrderDetail orderDetail = new OrderDetail() ;
            orderDetail.setOrder_id(order.getId());
            orderDetail.setAid(aid);
            orderDetail.setGid(c.getGoods().getGid());
            orderDetail.setGoodsNumber(c.getNumber());
            orderDetail.setGoodsParam(c.getParam());
            orderMapper.createOrderDetail(orderDetail) ;
        }

        return order;
    }

    @Override
    public List<Order> getAllOrderList(String token) {
        String uid = jedisClientPool.get(token) ;
        //orderMapper.findAllOrderListByUId(Integer.parseInt(uid)) ;
        return null;
    }

    @Override
    public List<Order> getOrderListByStatus(String token, Integer status) {
        return null;
    }

    @Override
    public List<CartList> verifyOrder(String token, List<Integer> ids) {
        String uid = jedisClientPool.get(token);
        List<CartList> cartLists = orderMapper.findCartDetailByIds(ids) ;
        return cartLists;
    }

    @Override
    public boolean updateOrderStruts(String token, String oid, Integer status) {
        return false;
    }
}
