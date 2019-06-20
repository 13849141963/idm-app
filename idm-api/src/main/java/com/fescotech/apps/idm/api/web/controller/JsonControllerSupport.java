package com.fescotech.apps.idm.api.web.controller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fesco.pafa.app.web.AbstractViewController;
import com.fesco.pafa.app.web.util.WebAccessUtil;
import com.fesco.pafa.exceptions.MessageException;
import com.fesco.pafa.util.JsonBuilder;
import com.fesco.pafa.util.StringUtil;
import com.fescotech.apps.idm.api.dto.Result;
import com.fescotech.apps.idm.api.util.IpAddressUtils;
import com.fescotech.apps.idm.api.util.LogUtils;
import com.fescotech.common.ext.biz.dao.impl.ExtendDaoT;
public  abstract class JsonControllerSupport<T>  extends AbstractViewController {
	//private static final long String = 0;
	private Class <T> clazz = null;
	/**
	 * 处理业务请求：该方法若抛出异常，返回Result对象封装<br/>
	 **/
	@SuppressWarnings("rawtypes")
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//返回结果
		Result<?> result = null;
		String msg= "";
		String logMethod = "";
		String logUrl = "";
		String ipAddr = "";
		String idmKey = "";
		String sign = "";
		String jsonData = "";
		long beginTime = 0;
		try {
			jsonData = getJsondata1(request,true);
			request.setAttribute("json", jsonData);
			T param = parseReqJson(jsonData);
			JSONObject requestJson = JSON.parseObject(jsonData);
			idmKey = requestJson.getString("idmKey");
			sign = requestJson.getString("sign");
			beginTime = System.currentTimeMillis();//开始时间 
			StringBuffer logUrlbf = request.getRequestURL();//访问地址
			logUrl = logUrlbf.toString();
			logMethod = request.getMethod();//执行方法
			ipAddr = IpAddressUtils.getIpAddress(request);//用户IP地址
			
			Object rslt = handle(param);
			if(rslt!=null){
			    if(rslt instanceof Result){
			    	result =(Result)rslt;
			    }else{
			    	result = Result.createSuccessResult(rslt);
			    }
			 } else {
				 result = Result.createSuccessResult();
			 }
			if(logger.isInfoEnabled())
				logger.info("本次请求："+request.getAttribute("json")+",返回数据："+JsonBuilder.buildJsonParam(result));
		}catch (Throwable e) {
			msg=e.getMessage();
			result = Result.createFailResult(msg);
			if(logger.isErrorEnabled())
				logger.error("本次请求："+request.getAttribute("json")+",发生异常："+StringUtil.getThrowableString(e));
		}finally {
			WebAccessUtil.writeHttpResponse(response,JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue));
			long endTime = System.currentTimeMillis();//结束时间
			LogUtils.insertLogs(idmKey,sign,jsonData, msg, beginTime, endTime, logUrl, logMethod, ipAddr);
		}
		return null;
	}
	 
	protected  Object handle(T param) throws Exception {
		try {
			return doHandle(param);	
		} finally{
			ExtendDaoT.clear();
		}
	}
	
	protected String getJsondata1(HttpServletRequest request,boolean checkNull)throws Exception{
		String jsondata = request.getParameter("jsondata");
		jsondata = request.getParameter("jsondata");
		if ((StringUtil.isEmpty(jsondata))) {
			jsondata = request.getParameter("jsonList");
		}
		if ((StringUtil.isEmpty(jsondata)) && (checkNull)) {
			throw new MessageException("jsondata为空");
		}
		return jsondata;
	}
	 
	
	/****
	 * 从流中读取json数据
	 * @param stream 数据流
	 * @return
	 * @throws Exception
	 */
	protected String readData(InputStream stream) throws Exception {
		StringBuffer res = new StringBuffer();
		char[] charBuf = new char[1024*10];
		int readChars = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
		while ((readChars=br.read(charBuf)) != -1){
		    res.append(charBuf,0, readChars);
		}
		return res.toString();
	}
	
	@SuppressWarnings("unchecked")
	protected T parseReqJson(String json){
		if(clazz == null){
			clazz = (Class <T>)getGenericClass(getClass());
		}
		
		return parseObject(json,clazz);
	}
	protected <P> P parseObject(String text,Class<P> clazzP){
		return JsonBuilder.parseObject(text.trim(),clazzP);
	}
	@SuppressWarnings({ "rawtypes" })
	protected Class getGenericClass(Class clacc){
		Class claxx = null;
		Type[] types = ((ParameterizedType) clacc.getGenericSuperclass()).getActualTypeArguments();
		if(types==null || types.length==0)
			return  null;
		Type trueType = types[0];
		if(trueType instanceof  java.lang.reflect.ParameterizedType){
			claxx  =  (Class)((ParameterizedType)trueType).getRawType();
		}else{
			claxx  =  (Class)trueType;
		}
		return claxx;
	}
	/**
	 * 处理业务请求：该方法若抛出异常，则构建一个Result对象，其errorMsg为异常Message，且code为1;<br/>
	 * 				否则如果返回结果是Result类型对象，则会直接转换为json返回给调用方；<br/>
	 * 				若不是会构建一个新的Result对象，code为0，并将返回结果作为其data值再返回给调用方。<br/>
	 * @param param 请求参数
	 * @return
	 */
	protected abstract Object doHandle(T param);
}
