package com.shasly.common.utils;

import java.util.UUID;

public class OrderUtils {

	public static String getOrderID() {
		
		UUID uuid = UUID.randomUUID() ;
		String id = uuid.toString().replaceAll("-", "").substring(0, 18).toUpperCase() ;
		
		return id ;
		
	}
}
