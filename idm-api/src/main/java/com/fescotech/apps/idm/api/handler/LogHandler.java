package com.fescotech.apps.idm.api.handler;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.apps.idm.api.util.IpAddressUtils;
import com.fescotech.apps.idm.base.domain.ApiLog;
import com.fescotech.apps.idm.base.domain.vo.ApiLogVo;
import com.fescotech.apps.idm.bootstrap.ServiceManager;

/**
 * 请求日志记录类
 * @author:lzl
 * @time:2017年6月29日 上午11:42:14
 */
public class LogHandler implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			startTimeThreadLocal.set(System.currentTimeMillis());
		} catch (Exception e) {
			logger.error("LogHandler 记录日志异常", e);
		} 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try {
			long beginTime = startTimeThreadLocal.get();//开始时间 
			long endTime = System.currentTimeMillis();//结束时间
			StringBuffer logUrl = request.getRequestURL();//访问地址
			String logMethod = request.getMethod();//执行方法
			String ipAddr = IpAddressUtils.getIpAddress(request);//用户IP地址
			Integer opResult = 1;//操作结果（1：成功    2：失败）
			String logMsg = "";//日志信息
			String idmKey = "";
			String loginName = "";
			String timeStamp = "";
			String sign = "";
			Map map = request.getParameterMap();  
			String requestParam = JsonBuilder.buildJsonParam(map);
			if (requestParam != null && !requestParam.equals("")) {
				logMsg = "请求参数：\r\n"+requestParam +"\r\n";
				idmKey = (String) map.get("idmKey");
				loginName = (String) map.get("loginName");
				timeStamp = (String) map.get("timeStamp");
				sign = (String) map.get("sign");
			}
			if (request.getAttribute("exception") != null && !request.getAttribute("exception").equals("")) {
				opResult = 2;
				logMsg += "异常信息：\r\n"+(String) request.getAttribute("exception");
			}
			ApiLogVo apiLogVo = ServiceManager.getApiLogService().queryLogMsg(sign);
			if (apiLogVo == null) {
				ApiLog baseLog = new ApiLog();
				baseLog.setLogId(sign);
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(beginTime);
				baseLog.setOpStartTime(c.getTime());
				Calendar c1 = Calendar.getInstance();
				c1.setTimeInMillis(endTime);
				baseLog.setOpEndTime(c1.getTime());
				baseLog.setLogUrl(logUrl.toString());
				baseLog.setHttpMethod(logMethod);
				baseLog.setIpAddr(ipAddr);
				baseLog.setOpResult(opResult);
				baseLog.setLogMsg(logMsg);
				baseLog.setUserName(idmKey);
				ServiceManager.getApiLogService().insert(baseLog);
			}
		} catch (Throwable e) {
			logger.error("LogHandler 记录日志异常", e);
		}finally{
			startTimeThreadLocal.remove();
		}
	}

}
