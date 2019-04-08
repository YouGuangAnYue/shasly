package com.shasly.common.bean;

import com.shasly.common.bean.Address;
import com.shasly.common.bean.Goods;

public class OrderDetail {

  private Integer id;
  private Integer order_id;
  private Integer aid ;
  private Integer gid ;
  private Integer goodsNumber;
  private String goodsParam;


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

  public Integer getAid() {
    return aid;
  }

  public void setAid(Integer aid) {
    this.aid = aid;
  }

  public Integer getGid() {
    return gid;
  }

  public void setGid(Integer gid) {
    this.gid = gid;
  }

  public Integer getGoodsNumber() {
    return goodsNumber;
  }

  public void setGoodsNumber(Integer goodsNumber) {
    this.goodsNumber = goodsNumber;
  }

  public String getGoodsParam() {
    return goodsParam;
  }

  public void setGoodsParam(String goodsParam) {
    this.goodsParam = goodsParam;
  }
}
