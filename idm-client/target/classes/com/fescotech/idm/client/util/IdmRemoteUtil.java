package com.fescotech.idm.client.util;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fesco.pafa.util.HttpClientUtil;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.idm.client.exception.IdmException;
import com.fescotech.idm.client.model.RemoteResult;

public class IdmRemoteUtil {
	/**
	 * 同步调用并返回封装对象
	 * 
	 * @param url 远程url
	 * @param request 请求Bean对象
	 * @return
	 */
	public static RemoteResult callRemoteService(String url, Object request) {
		Map<String, String> param = createJsonDataRequest(request);
		return callMicroService(url, param);
	}

	/**
	 * 同步调用并返回数据对象
	 * 
	 * @param url 远程服务地址
	 * @param request bean对象
	 * @return
	 */
	public static Object callHttpResult(String url, Object request) {
		Map<String, String> param = createJsonDataRequest(request);
		RemoteResult rsp = callMicroService(url, param);
		if (rsp == null) {
			throw new IdmException("远程调用返回对象为空");
		}
		return rsp.getSuccessResult();
	}

	private static RemoteResult callMicroService(String url, Map<String, String> param) {
		String rslt = HttpClientUtil.httpPostProxy(url, param, null, 0);
		if (rslt == null ||  rslt.trim().equals("")) {
			throw new IdmException("访问认证服务失败，未返回任何内容!");
		}
		try {
			return JsonBuilder.parseObject(rslt, RemoteResult.class);
		} catch (Exception e) {
			throw new IdmException("认证服务暂不可用或者返回内容格式异常!");
		}	
		 
	}
	private static Map<String, String> createJsonDataRequest(Object request) {
		String json = null;
		if (request instanceof String){
			json = (String) request;
		}
		else {
			json = JSON.toJSONString(request);
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("jsondata", json);
		return param;
	}
}
