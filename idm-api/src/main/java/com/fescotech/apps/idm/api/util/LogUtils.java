package com.fescotech.apps.idm.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.fescotech.apps.idm.base.domain.ApiLog;
import com.fescotech.apps.idm.bootstrap.ServiceManager;
import com.fescotech.common.util.StringHelper;

public class LogUtils {

	public static void insertLogs(String idmKey, String sign, String requestData, String errorMsg, long beginTime
			,long endTime, String logUrl, String logMethod, String ipAddr){
		try {
			int opResult = 1;
			ApiLog baseLog = new ApiLog();
			if(StringHelper.isEmpty(sign)){
				Date now = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
				String timeStamp = sdf.format(now);
				sign = new Sha256Hash("temp"+timeStamp).toHex();
			}
			baseLog.setLogId(sign);
			String logMsg = "请求参数：\r\n"+requestData+"\r\n";
			if(!errorMsg.equals("")) {
				opResult = 2;
				logMsg = logMsg + "异常信息：\r\n" + errorMsg;
			}
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(beginTime);
			baseLog.setOpStartTime(c.getTime());
			Calendar c1 = Calendar.getInstance();
			c1.setTimeInMillis(endTime);
			baseLog.setOpStartTime(c.getTime());
			baseLog.setOpEndTime(c1.getTime());
			baseLog.setLogUrl(logUrl.toString());
			baseLog.setHttpMethod(logMethod);
			baseLog.setIpAddr(ipAddr);
			baseLog.setOpResult(opResult);
			baseLog.setLogMsg(logMsg);
			baseLog.setUserName(idmKey);
			ServiceManager.getApiLogService().insert(baseLog);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
