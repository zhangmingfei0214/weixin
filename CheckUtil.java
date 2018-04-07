package com.imooc.util;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {
	private static final String token = "imooc";
	public static boolean checkSignature (String signature,String timestamp,String nonce) {
		String[] arr = new String[] {token,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content = new StringBuffer();
		for(int i=0;i<arr.length;i++) {
			content.append(arr[i]);
		}
		//shal加密
		String temp = getShal(content.toString());
		return temp.equals(signature);
}
	
			public static String getShal(String str) {
				if(str == null||str.length()==0) {
					return null;
				}
				char hexDigits[] = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
				MessageDigest mdTemp = MessageDigest.getInstance("SHAl");
				mdTemp.update(str.getBytes("UTF-8"));
				byte[] md = mdTemp.digest();
				int j = md.length;
			}
			
		} 
