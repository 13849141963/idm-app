package com.fescotech.idm.client.support;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fesco.pafa.exceptions.MessageException;
import com.fesco.pafa.util.JsonBuilder;
import com.fescotech.idm.client.exception.IdmException;
import com.fescotech.idm.client.model.IdmUser;
import com.fescotech.idm.client.model.IdmUserDto;
import com.fescotech.idm.client.model.RemoteResult;
import com.fescotech.idm.client.model.ResultFunctionDto;
import com.fescotech.idm.client.util.EncryptUtil;
import com.fescotech.idm.client.util.IdmConfig;
import com.fescotech.idm.client.util.IdmOpenUtil;
import com.fescotech.idm.client.util.IdmRemoteUtil;
public class IdmUserSupport {
	
	/**
	 * 通过开放平台调用idm登录验证服务，idmKey与登录地址从idmconfig配置文件读取
	 * @param loginName 登陆账号
	 * @param userPwd 密码
	 * @return IdmUser
	 */
	public static IdmUser checkSysUserByOpenApi(String loginName,String userPwd){
		String idmKey = IdmConfig.me().getPropValue("idmKey");
		String openUrl = IdmConfig.me().getPropValue("openapi.url");
		String appkey = IdmConfig.me().getPropValue("openapi.appKey");
		String appSecret = IdmConfig.me().getPropValue("openapi.appSecret");
		String loginMethodName = IdmConfig.me().getPropValue("openapi.idmLoginMethod");
		return checkSysUserByOpen(idmKey,loginName,userPwd,openUrl,appkey,appSecret,loginMethodName);
	}
	
	
	/**
	 * 用户登录验证
	 * @param idmKey 统一用户系统分配的登录系统Key(应用别名)
	 * @param loginName 用户登录名
	 * @param userPwd  用户密码
	 * @param idmUrl 登陆服务地址
	 * @return IdmUser
	 */
	public static IdmUser checkSysUserByOpen(String idmKey,String loginName,String userPwd,String openUrl,String appKey,String appSecret,String methodName){
		checkParam(idmKey,loginName,userPwd,openUrl,appKey,appSecret,methodName);
		userPwd = EncryptUtil.SHA256(userPwd);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        String checkSign = EncryptUtil.SHA256(idmKey+loginName+timeStamp+userPwd);
        IdmUserDto sud =new IdmUserDto();
        sud.setLoginName(loginName);
		sud.setSign(checkSign);
		sud.setTimeStamp(timeStamp);
		sud.setIdmKey(idmKey);
		IdmUser rslt = (IdmUser) remoteOpenCall(sud, openUrl, IdmUser.class,appKey,appSecret,methodName);
		return rslt;
	}
		
	private static void checkParam(String idmKey,String loginName,String userPwd,String openUrl,String appKey,String appSecret,String methodName){
		if(idmKey==null || idmKey.trim().equals("")){
			throw new IdmException("未配置idmKey!");
		}
		if(loginName == null || loginName.trim().equals("")){
			throw new MessageException("登录名缺失");
		}
		if(userPwd == null || userPwd.trim().equals("")){
			throw new MessageException("密码缺失");
		}
		if(openUrl==null || openUrl.trim().equals("")){
			throw new IdmException("未配置开放平台访问地址-openUrl!");
		}
		if(appKey==null || appKey.trim().equals("")){
			throw new IdmException("未配置开放平台应用标识-appKey!");
		}
		if(appSecret==null || appSecret.trim().equals("")){
			throw new IdmException("未配置开放平台应用密钥-appSecret!");
		}
		if(methodName==null || methodName.trim().equals("")){
			throw new IdmException("未配置开放平台登录访问方法-idmLoginMethod!");
		}
	}
	
	/**
	 * 调用idm登录验证服务，idmKey与登录地址从idmconfig配置文件读取
	 * @param loginName 登陆账号
	 * @param userPwd 密码
	 * @return IdmUser
	 */
	public static IdmUser checkSysUser(String loginName,String userPwd){
		String idmKey = IdmConfig.me().getPropValue("idmKey");
		String idmUrl = IdmConfig.me().getPropValue("idmUrl");
		return checkSysUser(idmKey,loginName,userPwd,idmUrl);
	}
	
	/**
	 * 用户登录验证
	 * @param idmKey 统一用户系统分配的登录系统Key(应用别名)
	 * @param loginName 用户登录名
	 * @param userPwd  用户密码(未加密)
	 * @param idmUrl 登陆服务地址
	 * @return IdmUser
	 */
	public static IdmUser checkSysUser(String idmKey,String loginName,String userPwd,String idmUrl){
		checkParam(idmKey, loginName, userPwd, idmUrl);
		userPwd = EncryptUtil.SHA256(userPwd);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        String checkSign = EncryptUtil.SHA256(idmKey+loginName+timeStamp+userPwd);
        IdmUserDto sud =new IdmUserDto();
        sud.setLoginName(loginName);
		sud.setSign(checkSign);
		sud.setTimeStamp(timeStamp);
		sud.setIdmKey(idmKey);
		IdmUser rslt = (IdmUser) remoteCall(sud, idmUrl, IdmUser.class);
		return rslt;
	}
	
	/**
	 * 调用idm修改用户密码，idmKey与登录地址从idmconfig配置文件读取
	 * @param loginName 用户登录名
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @return IdmUser
	 */
	public static IdmUser changePassword(String loginName,String oldPassword,String newPassword){
		String idmKey = IdmConfig.me().getPropValue("idmKey");
		String idmUrl = IdmConfig.me().getPropValue("idmChangePwdUrl");
		return changePassword(idmKey,loginName,oldPassword,newPassword,idmUrl);
	}
	
	/**
	 * idm修改用户密码
	 * @param idmKey 统一用户系统分配的登录系统Key(应用别名)
	 * @param loginName 用户登录名
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @param idmUrl 登陆服务地址
	 * @return IdmUser
	 */
	public static IdmUser changePassword(String idmKey,String loginName,String oldPassword,String newPassword,String idmUrl){
		if(newPassword == null || newPassword.trim().equals("")){
			throw new MessageException("新密码缺失");
		}
		checkParam(idmKey, loginName, oldPassword, idmUrl);
		newPassword = EncryptUtil.SHA256(newPassword);
		oldPassword = EncryptUtil.SHA256(oldPassword);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        String checkSign = EncryptUtil.SHA256(idmKey+loginName+timeStamp+oldPassword);
        IdmUserDto sud =new IdmUserDto();
        sud.setIdmKey(idmKey);
        sud.setLoginName(loginName);
        sud.setTimeStamp(timeStamp);
		sud.setSign(checkSign);
		sud.setNewPassword(newPassword);
		IdmUser rslt = (IdmUser) remoteCall(sud, idmUrl, IdmUser.class);
		return rslt;
	}
	
	/**
	 * 调用idm查询用户信息，idmKey与登录地址从idmconfig配置文件读取
	 * @param loginName 用户登录名
	 * @param userPwd 密码
	 * @return IdmUser
	 */
	public static IdmUser queryUser(String loginName,String userPwd){
		String idmKey = IdmConfig.me().getPropValue("idmKey");
		String idmUrl = IdmConfig.me().getPropValue("idmUserQuery");
		return queryUser(idmKey,loginName,idmUrl,userPwd);
	}
	
	/**
	 * idm查询用户信息
	 * @param idmKey 统一用户系统分配的登录系统Key(应用别名)
	 * @param loginName 用户登录名
	 * @param idmUrl 登陆服务地址
	 * @param userPwd 密码
	 * @return IdmUser
	 */
	public static IdmUser queryUser(String idmKey,String loginName,String idmUrl,String userPwd){
		checkParam(idmKey, loginName, userPwd, idmUrl);
		userPwd = EncryptUtil.SHA256(userPwd);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        String checkSign = EncryptUtil.SHA256(idmKey+loginName+timeStamp+userPwd);
        IdmUserDto sud =new IdmUserDto();
        sud.setIdmKey(idmKey);
        sud.setLoginName(loginName);
        sud.setTimeStamp(timeStamp);
		sud.setSign(checkSign);
		IdmUser rslt = (IdmUser) remoteCall(sud, idmUrl, IdmUser.class);
		return rslt;
	}
	
	/**
	 * 调用idm查询用户权限信息，idmKey与登录地址从idmconfig配置文件读取
	 * @param loginName 用户登录名
	 * @param userPwd 密码
	 * @return List<ResultFunctionDto>
	 */
	public static List<ResultFunctionDto> queryUserFunction(String loginName,String userPwd){
		String idmKey = IdmConfig.me().getPropValue("idmKey");
		String idmUrl = IdmConfig.me().getPropValue("idmUserFunction");
		return queryUserFunction(idmKey,loginName,idmUrl,userPwd);
	}
	
	/**
	 * idm查询用户权限信息
	 * @param idmKey 统一用户系统分配的登录系统Key(应用别名)
	 * @param loginName 用户登录名
	 * @param idmUrl 登陆服务地址
	 * @param userPwd 密码
	 * @return List<ResultFunctionDto>
	 */
	@SuppressWarnings("unchecked")
	public static List<ResultFunctionDto> queryUserFunction(String idmKey,String loginName,String idmUrl,String userPwd){
		checkParam(idmKey, loginName, userPwd, idmUrl);
		userPwd = EncryptUtil.SHA256(userPwd);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
        String checkSign = EncryptUtil.SHA256(idmKey+loginName+timeStamp+userPwd);
        IdmUserDto sud =new IdmUserDto();
        sud.setIdmKey(idmKey);
        sud.setLoginName(loginName);
        sud.setTimeStamp(timeStamp);
		sud.setSign(checkSign);
		List<ResultFunctionDto> rslt = (List<ResultFunctionDto>) remoteCallList(sud, idmUrl, ResultFunctionDto.class);
		return rslt;
	}
	
	private static void checkParam(String idmKey,String loginName,String userPwd,String idmUrl){
		if(idmKey==null || idmKey.trim().equals("")){
			throw new IdmException("未配置idmKey!");
		}
		if(loginName == null || loginName.trim().equals("")){
			throw new MessageException("登录名缺失");
		}
		if(userPwd == null || userPwd.trim().equals("")){
			throw new MessageException("密码缺失");
		}
		if(idmUrl==null || idmUrl.trim().equals("")){
			throw new IdmException("未配置用户认证登录地址-idmUrl!");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object remoteOpenCall(IdmUserDto sud,String openUrl, Class className,String appKey, String appSecret, String methodName){
		RemoteResult res = IdmOpenUtil.callOpenService(openUrl, appKey, appSecret, methodName, sud);
		if (!"0".equals(res.getCode())) {
			throw new IdmException(res.getErrorMsg());
		}
		Object data = res.getSuccessResult();
		String json = String.valueOf(data);
		Object rslt =JsonBuilder.parseObject(json, className);
		return rslt;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object remoteCall(IdmUserDto sud,String idmUrl, Class className){
		RemoteResult res = IdmRemoteUtil.callRemoteService(idmUrl, sud);
		if (!"0".equals(res.getCode())) {
			throw new IdmException(res.getErrorMsg());
		}
		Object data = res.getSuccessResult();
		String json = String.valueOf(data);
		Object rslt =JsonBuilder.parseObject(json, className);
		return rslt;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Object remoteCallList(IdmUserDto sud,String idmUrl, Class className){
		RemoteResult res = IdmRemoteUtil.callRemoteService(idmUrl, sud);
		if (!"0".equals(res.getCode())) {
			throw new IdmException(res.getErrorMsg());
		}
		Object data = res.getSuccessResult();
		String json = String.valueOf(data);
		Object rslt =JsonBuilder.parseArray(json, className);
		return rslt;
	}
	
}
