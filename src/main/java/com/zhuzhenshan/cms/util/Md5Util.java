package com.zhuzhenshan.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @ClassName: Md5Util 
 * @Description: 对密码进行加密
 * @author: 朱振山
 * @date: 2020年3月12日 上午9:51:55
 */
public class Md5Util {
	
	private static String salt="qazwsx";
	
	public static String encode(String password) {
		
		return DigestUtils.md5Hex(password+salt);
	}
	
}
