package com.shasly.common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartList {

	private int id;
	private String param ;
	private int number ;
	private Goods goods ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
