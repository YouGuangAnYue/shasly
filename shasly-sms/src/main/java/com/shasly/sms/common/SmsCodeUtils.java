package com.shasly.sms.common;

import redis.clients.jedis.Jedis;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 这是生成验证码、封装验证码到redis、从redis取验证码的工具类
 */
public class SmsCodeUtils {
    private static final String SYMBOLS = "0123456789"; // 数字

    // 字符串
    // private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();
    

    public static String getNonce_str() {

        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[6];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }

        return new String(nonceChars);
    }

    public static String smsCode(String mobile,Jedis jedis){
        jedis.setex(mobile,60*30,SmsCodeUtils.getNonce_str());
        String validatecode = jedis.get(mobile);
        return validatecode;
    }

    public static String getSmsCode(String mobile,Jedis jedis){
        String validatecode = jedis.get(mobile);
        return validatecode;
    }


}
