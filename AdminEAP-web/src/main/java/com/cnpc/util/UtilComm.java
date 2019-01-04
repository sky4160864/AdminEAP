package com.cnpc.util;

public class UtilComm {
	public static String obj2Str(Object ob){
		if(ob==null)return "";
		if(ob instanceof String){
			return (String)ob;
		}else {
			System.out.println(ob+"not String ");
			return "";
		}
	}

	public static String pad(String str,int len){
		return pad(str, len, "0");
	}

	public static String pad(String str,int len,String pls){
		int slen = str.length();
		if(slen>len)return str.substring(0,len);
		while(slen<len){
			str=pls+str;
			slen++;
		}
		return str;
	}

	public static String padBehind(String str,int len){
		int slen = str.length();
		if(slen>len)return str.substring(0,len);
		while(slen<len){
			str=str+" ";
			slen++;
		}
		return str;
	}

	/**
	 * 验证ip是否合法
	 *
	 * @param text
	 *            ip地址
	 * @return 验证信息
	 */
	public static boolean ipCheck(String text) {
		if (text != null) {
			// 定义正则表达式
			String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
					+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
			// 判断ip地址是否与正则表达式匹配
			if (text.matches(regex)) {
				// 返回判断信息
				return true;
			}
		}
		return false;
	}
}
