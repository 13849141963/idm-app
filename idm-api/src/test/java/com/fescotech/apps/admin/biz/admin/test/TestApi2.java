package com.fescotech.apps.admin.biz.admin.test;

import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.apps.idm.api.dto.*;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestApi2 {
	
	//String url = "http://10.0.75.21:8080";
//	String url = "http://127.0.0.1:8080";
	//String url = "http://10.0.45.215:8080";
	String url = "http://192.168.66.1:8080";

	//用户登录
	@Test
	public void userLogin(){
		String idmKey = "统一用户管理系统";
		String loginName = "admin1";
		String pwd=new Sha256Hash("admin1").toHex();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+loginName+timeStamp+pwd).toHex();


		UserCheckDto su =new UserCheckDto();
		su.setIdmKey(idmKey);
		su.setLoginName(loginName);
		su.setTimeStamp(timeStamp);
		su.setSign(sign);
		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/user/login-check.asmx", su);
		String json = JsonBuilder.buildJsonParam(data);
		System.out.println(json);
	}



	//用户登录接口
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


	//用户修改密码接口
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


   	//查询用户信息接口
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


	//对应的接口为UserQueryByRoleIdController
	//通过角色id查询用户的角色名称返回的是BASE_ROLE数据表格的信息
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



	//对应的接口为RoleQueryByUserIdController
	//通过用户id查询用户的角色名称返回的是BASE_ROLE数据表格的信息
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

	//对应的接口为RoleQueryByUserIdController
	//添加公司
	@Test
	public void saveCorpInfiController(){
		String idmKey = "phexama";
		String userId = "241";
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		String timeStamp = sdf.format(now);
		String sign = new Sha256Hash(idmKey+userId+timeStamp).toHex();


		CorpInfoDto corpInfoDto = new  CorpInfoDto();
		corpInfoDto.setCompanyName("北京文化传媒责任有限公司");
		corpInfoDto.setShortName("文化传媒");



		Result<?> data = HttpApiUtil.callSyncService(url + "/idm-api/crop/crop_saveCropInfo.asmx", corpInfoDto);
		String json = JsonBuilder.buildJsonParam(data);
		System.out.println(json);
	}
    
}