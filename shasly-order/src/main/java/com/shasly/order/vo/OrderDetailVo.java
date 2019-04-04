package com.shasly.order.vo;

import com.shasly.common.bean.Address;
import com.shasly.common.bean.Goods;

public class OrderDetailVo {
    private Integer id ;
    private Integer order_id ;
    private Address address ;
    private Goods goods ;
    private Integer goods_number ;
    private String goods_param ;

    public OrderDetailVo() {

    }

    public OrderDetailVo(Integer id, Integer order_id, Address address, Goods goods, Integer goods_number, String goods_param) {
        this.id = id;
        this.order_id = order_id;
        this.address = address;
        this.goods = goods;
        this.goods_number = goods_number;
        this.goods_param = goods_param;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(Integer goods_number) {
        this.goods_number = goods_number;
    }

    public String getGoods_param() {
        return goods_param;
    }

    public void setGoods_param(String goods_param) {
        this.goods_param = goods_param;
    }

    @Override
    public String toString() {
        return "OrderDetailVo{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", address=" + address +
                ", goods=" + goods +
                ", goods_number=" + goods_number +
                ", goods_param='" + goods_param + '\'' +
                '}';
    }
}
