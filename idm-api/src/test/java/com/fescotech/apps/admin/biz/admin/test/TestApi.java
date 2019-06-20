package com.fescotech.apps.admin.biz.admin.test;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.apps.idm.api.dto.Result;
import com.fescotech.apps.idm.api.dto.RoleQueryByUserIdDto;
import com.fescotech.apps.idm.api.dto.UserChangePwdDto;
import com.fescotech.apps.idm.api.dto.UserCheckDto;
import com.fescotech.apps.idm.api.dto.UserQueryByRoleIdDto;
import com.fescotech.apps.idm.api.dto.UserQueryDto;
public class TestApi   {  
	
//	String url = "http://10.0.75.21:8080";
	String url = "http://127.0.0.1:8080";

    @Test
	public void getUser(){ 
		String idmKey = "补医保整合系统";
		String loginName = "phexamatest";
		String pwd=new Sha256Hash("phexamatest").toHex();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+loginName+timeStamp).toHex();
		
		UserCheckDto su =new UserCheckDto();
		su.setIdmKey(idmKey);
		su.setLoginName(loginName);
		su.setTimeStamp(timeStamp);
		su.setSign(sign);
		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/user/login-check.asmx", su);
		String json = JsonBuilder.buildJsonParam(data);
		System.out.println(json);
	}
    
    @Test
   	public void changeUserPwd(){ 
    	String idmKey = "补医保整合系统";
		String loginName = "phexamatest";
		String pwd=new Sha256Hash("phexamatest").toHex();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+loginName+timeStamp+pwd).toHex();
    	
       	UserChangePwdDto su =new UserChangePwdDto();
       	su.setIdmKey(idmKey);
		su.setLoginName(loginName);
		su.setTimeStamp(timeStamp);
		su.setSign(sign);
		String newPassWord = new Sha256Hash("phexamatest").toHex();
   		su.setNewPassword(newPassWord);
   		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/user/change_pwd.asmx", su);
   		String json = JsonBuilder.buildJsonParam(data);
   		System.out.println(json);
   	}
    
    @Test
    public void getUserQuery(){ 
    	String idmKey = "补医保整合系统";
		String loginName = "phexamatest";
		String pwd=new Sha256Hash("phexamatest").toHex();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+loginName+timeStamp+pwd).toHex();
		
    	UserQueryDto su =new UserQueryDto();
    	su.setLoginName(loginName);
    	su.setIdmKey("补医保整合系统");
    	su.setSign(sign);
    	su.setTimeStamp(timeStamp);
    	Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/user/user_query.asmx", su);
    	String json = JsonBuilder.buildJsonParam(data);
    	System.out.println(json);
    }
    
    @Test
	public void getUserFunction(){ 
    	String idmKey = "phexama";
		String loginName = "phexamatest";
		String pwd=new Sha256Hash("phexamatest").toHex();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+loginName+timeStamp+pwd).toHex();
		
    	UserCheckDto su =new UserCheckDto();
		su.setLoginName(loginName);
		su.setIdmKey("phexama");
		su.setSign(sign);
    	su.setTimeStamp(timeStamp);
		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/user/user_function.asmx", su);
		String json = JsonBuilder.buildJsonParam(data);
		System.out.println(json);
	}
    
    @Test
	public void getUserListByRoleId(){ 
    	String idmKey = "补医保整合系统";
		String roleId = "71";
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+roleId+timeStamp).toHex();
		
		UserQueryByRoleIdDto su =new UserQueryByRoleIdDto();
		su.setRoleId(roleId);
		su.setIdmKey("补医保整合系统");
		su.setSign(sign);
    	su.setTimeStamp(timeStamp);
		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/user/user_queryByRoleId.asmx", su);
		String json = JsonBuilder.buildJsonParam(data);
		System.out.println(json);
	}
    
    @Test
	public void getRoleListByUserId(){ 
    	String idmKey = "phexama";
		String userId = "241";
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+userId+timeStamp).toHex();
		
		RoleQueryByUserIdDto su =new RoleQueryByUserIdDto();
		su.setUserId(userId);
		su.setIdmKey("phexama");
		su.setSign(sign);
    	su.setTimeStamp(timeStamp);
    	su.setIden("1");
		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/role/role_queryByUserId.asmx", su);
		String json = JsonBuilder.buildJsonParam(data);
		System.out.println(json);
	}
    
}