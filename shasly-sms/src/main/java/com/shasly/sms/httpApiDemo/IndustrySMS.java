package com.shasly.sms.httpApiDemo;

import com.shasly.sms.common.Config;
import com.shasly.sms.common.HttpUtil;
import com.shasly.sms.common.SmsCodeUtils;
import redis.clients.jedis.Jedis;

import java.net.URLEncoder;


/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";
	private static String accountSid = Config.ACCOUNT_SID;
	//private static String smsContent = "【贝曼衣舍】您的验证码为"+SmsCodeUtils.smsCode()+"，请于"+30+"分钟内正确输入，如非本人操作，请忽略此短信。";

	/**
	 * 验证码通知短信
	 */
	public static String execute(String to, Jedis jedis)
	{
		String tmpSmsContent = null;
		String smsContent = "【贝曼衣舍】您的验证码为"+SmsCodeUtils.smsCode(to,jedis)+"，请于"+30+"分钟内正确输入，如非本人操作，请忽略此短信。";
	    try{
	      tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
	    }catch(Exception e){
	      
	    }
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
	        + HttpUtil.createCommonParam();

	    // 提交请求
	    String result = HttpUtil.post(url, body);
	    return System.lineSeparator() + result;
	   // System.out.println("result:" + System.lineSeparator() + result);
	}
}
