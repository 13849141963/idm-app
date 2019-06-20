package com.fescotech.apps.idm.api.web.controller;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.UserQueryDto;
import com.fescotech.apps.idm.api.util.CheckSysName;
import com.fescotech.apps.idm.api.web.support.IdmSupport;
import com.fescotech.common.util.StringHelper;

/**
 * 用户信息查询接口
 * @author:lzl
 * @time:2017年9月13日 下午3:43:37
 */
public class UserQueryController extends JsonControllerSupport<UserQueryDto>{
	@Override
	protected Object doHandle(UserQueryDto user) {
		String loginName = user.getLoginName();
		String timeStamp = user.getTimeStamp();
		String sign = user.getSign();
		CheckSysName.doHandle(user);
		if(StringHelper.isEmpty(loginName)){
			throw new MessageException("登录名缺失");
		}
		if(StringHelper.isEmpty(timeStamp)){
			throw new MessageException("时间戳缺失");
		}
		if(StringHelper.isEmpty(sign)){
			throw new MessageException("签名缺失");
		}
		return IdmSupport.queryUserInfor(user.getLoginName(), sign, user.getIdmKey(), timeStamp);
	}

}
