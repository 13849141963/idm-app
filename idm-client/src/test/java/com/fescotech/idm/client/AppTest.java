package com.fescotech.idm.client;
import java.util.List;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.idm.client.model.IdmUser;
import com.fescotech.idm.client.model.ResultFunctionDto;
import com.fescotech.idm.client.support.IdmUserSupport;
public class AppTest {
	/*
	 * 测试数据返回结果示例
	 * 	testRemote()：
	 *  {"loginName":"test_h3c","perms":["sys:manage"],"sex":1,"userId":101,"userName":"test_h3c","userStatus":1,"userType":1}
		testRemoteQueryUser()：
		{"loginName":"test_h3c","orgId":108,"orgName":"h3c","sex":1,"userId":101,"userName":"test_h3c","userStatus":1,"userType":1}
		testRemoteQueryUserFunction()：
		[{"functionId":287,"functionName":"系统管理","functionType":1,"perm":"sys:manage"}]
		testRemoteChangePwd()：
		{"corpId":1,"createTime":1513151574000,"loginName":"test_h3c","orgId":108,"sex":1,"userId":101,"userName":"test_h3c","userStatus":1,"userType":1}
	 */
	private static void testRemoteOpen(){
		String loginName = "test_h3c";
		String password  = "test_h3c";
		IdmUser user = IdmUserSupport.checkSysUserByOpenApi(loginName, password);
		System.out.println(JsonBuilder.buildJsonParam(user));
	}
	
	private static void testRemote(){
		String loginName = "test_h3c";
		String password  = "test_h3c";
		IdmUser user = IdmUserSupport.checkSysUser(loginName, password);
		System.out.println(JsonBuilder.buildJsonParam(user));
	}
	
	public static void main(String[] args){
		testRemoteOpen();
		//testRemote();
//		testRemoteQueryUser();
//		testRemoteQueryUserFunction();
//		testRemoteChangePwd();
	}
	
	@SuppressWarnings("unused")
	private static void testRemoteQueryUser(){
		String loginName = "test_h3c";
		String userPwd  = "test_h3c";
		IdmUser user = IdmUserSupport.queryUser(loginName, userPwd);
		System.out.println(JsonBuilder.buildJsonParam(user));
	}
	
	@SuppressWarnings("unused")
	private static void testRemoteQueryUserFunction(){
		String loginName = "test_h3c";
		String userPwd  = "test_h3c";
		List<ResultFunctionDto> user = IdmUserSupport.queryUserFunction(loginName, userPwd);
		System.out.println(JsonBuilder.buildJsonParam(user));
	}
	
	@SuppressWarnings("unused")
	private static void testRemoteChangePwd(){
		String loginName = "test_h3c";
		String oldPassword  = "123";
		String newPassword  = "test_h3c";
		IdmUser user = IdmUserSupport.changePassword(loginName, oldPassword, newPassword);
		System.out.println(JsonBuilder.buildJsonParam(user));
	}
     
}
