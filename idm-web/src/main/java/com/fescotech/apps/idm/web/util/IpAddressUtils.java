package com.fescotech.apps.idm.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户真实IP地址工具类
 * @author:lzl
 * @time:2017年6月30日 上午11:09:18
 */
public class IpAddressUtils {

	public final static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For"); 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader("Proxy-Client-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");  
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr(); 
			}
		} else if (ip.length() > 15) { 
			String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
		}
		return ip; 
	}
	
}
