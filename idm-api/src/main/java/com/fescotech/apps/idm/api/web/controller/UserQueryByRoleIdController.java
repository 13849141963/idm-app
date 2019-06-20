package com.fescotech.apps.idm.api.web.controller;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.UserQueryByRoleIdDto;
import com.fescotech.apps.idm.api.util.CheckSysName;
import com.fescotech.apps.idm.api.web.support.IdmSupport;
import com.fescotech.common.util.StringHelper;

/**
 * 根据角色ID查询用户数据集接口
 * @author:lzl
 * @time:2017年9月13日 下午3:43:37
 */
public class UserQueryByRoleIdController extends JsonControllerSupport<UserQueryByRoleIdDto>{
	@Override
	protected Object doHandle(UserQueryByRoleIdDto user) {
		String roleId = user.getRoleId();
		String timeStamp = user.getTimeStamp();
		String sign = user.getSign();
		CheckSysName.doHandle(user);
		if(StringHelper.isEmpty(roleId)){
			throw new MessageException("角色ID缺失");
		}
		if(StringHelper.isEmpty(timeStamp)){
			throw new MessageException("时间戳缺失");
		}
		if(StringHelper.isEmpty(sign)){
			throw new MessageException("签名缺失");
		}
		return IdmSupport.queryUserInforByRoleId(roleId, sign, user.getIdmKey(), timeStamp);
	}
	
}
