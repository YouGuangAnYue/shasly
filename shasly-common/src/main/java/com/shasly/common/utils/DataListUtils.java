package com.shasly.common.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataListUtils {



	public static List<Address> getAddressList(HttpServletRequest request, HttpServletResponse response) {
		AddressService service = new AddressServiceImpl();

		// 获取用户id
		User user = (User) request.getSession().getAttribute("cart");
		int uid = user.getId();

		// 获取地址集合
		List<Address> addresses = service.findByUId(uid);

		return addresses;

	}

	public static List<Order> getOrderList(HttpServletRequest request, HttpServletResponse response) {
		OrderService service = new OrderServiceImpl();

		// 获取用户id
		User user = (User) request.getSession().getAttribute("cart");
		int uid = user.getId();
		
		//获取订单集合
		List<Order> orders = service.findByUId(uid) ;
		
		return orders ;

	}
}
