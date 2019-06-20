package com.fescotech.idm.client.util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.fesco.pafa.util.HttpClientUtil;
import com.fesco.pafa.util.JsonBuilder;
import com.fesco.pafa.util.StringUtil;
import com.fescotech.idm.client.exception.IdmException;
import com.fescotech.idm.client.model.RemoteResult;
public class IdmOpenUtil {
	/**
	 * 同步调用并返回封装对象
	 * @param url 远程url
	 * @param request 请求Bean对象
	 * @return
	 */
	public static RemoteResult callOpenService(String url,String appKey,String appSecret,String methodName,Object  reqObj) {
		String reqJson = createJsonDataRequest(reqObj);
		String rslt = callOpenApi(url,appKey,appSecret,methodName,null,reqJson,null,0);
		if (rslt == null ||  rslt.trim().equals("")) {
			throw new IdmException("访问认证服务失败，未返回任何内容!");
		}
		try {
			return JsonBuilder.parseObject(rslt, RemoteResult.class);
		} catch (Exception e) {
			throw new IdmException("认证服务暂不可用或者返回内容格式异常!");
		}
	}
	
	private static String createJsonDataRequest(Object request) {
		String json = null;
		if (request instanceof String){
			json = (String) request;
		}
		else {
			json = JSON.toJSONString(request);
		}
		return json;
	}
	
	private static String callOpenApi(String reqUrl,String appKey,String appSecret,String methodName,String jsonParamName,String reqJson,String format,int timeout){
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("methodName", methodName);
		parameters.put("timeStamp", String.valueOf(System.currentTimeMillis()));
		parameters.put("appkey", appKey);
		if(StringUtil.isEmpty(format)){
			format="json";
		}
		parameters.put("format",format);
		if(StringUtil.isEmpty(jsonParamName)){
			jsonParamName="jsonList";
		}
		parameters.put(jsonParamName, reqJson);
		String mySign =  signHttpRequestByMd5(parameters , appSecret);
		parameters.put("sign", mySign);	
		String result = HttpClientUtil.httpPost(reqUrl, parameters, timeout);
		return  result;
	}
	
	private static String signHttpRequestByMd5(Map<String, String> paramMap,
			String args) {
		String[] keyArray = (String[]) paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(args);
		for(String key : keyArray) {
			if (StringUtil.isEmpty(paramMap.get(key))) {
				continue;
			}
			stringBuffer.append(key).append(String.valueOf(paramMap.get(key)));
		}
		return Md5Util.MD5Encode(stringBuffer.toString());
	}
}
