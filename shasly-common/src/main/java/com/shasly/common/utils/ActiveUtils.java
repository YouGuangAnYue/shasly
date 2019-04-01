package com.shasly.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActiveUtils {

	public static String activeCode() {
		Date date = new Date() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
		String s1 = sdf.format(date) ;
		String s2=Integer.toHexString((int)(Math.random()*900)+100);
		return s1 + s2 ;
	}
	
}
