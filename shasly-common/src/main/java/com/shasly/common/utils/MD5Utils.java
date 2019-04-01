package com.shasly.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {
	/**
	 * md5加密
	 * @return
	 */
	public static String md5(String password) {
		
		try {
			//获取MD5转换器
			MessageDigest messageDigest=MessageDigest.getInstance("md5");
			//更新加密数组
			messageDigest.update(password.getBytes("utf-8"));
			//System.out.println(messageDigest.digest().length);
			return new BigInteger(1, messageDigest.digest()).toString(16);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
