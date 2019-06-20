package com.fescotech.apps.idm.api.web.controller;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.UserChangePwdDto;
import com.fescotech.apps.idm.api.util.CheckSysName;
import com.fescotech.apps.idm.api.web.support.IdmSupport;
import com.fescotech.common.util.StringHelper;

/**
 * 用户修改密码接口实现
 * @author:lzl
 * @time:2017年6月28日 下午4:13:31
 */
public class UserChangePwdController extends JsonControllerSupport<UserChangePwdDto>{
	@Override
	protected Object doHandle(UserChangePwdDto param) {
		String idmKey = param.getIdmKey();
		String loginName = param.getLoginName();
		String timeStamp = param.getTimeStamp();
		String sign = param.getSign();
		String newPassword = param.getNewPassword();
		CheckSysName.doHandle(param);
		if(StringHelper.isEmpty(loginName)){
			throw new MessageException("登录名缺失");
		}
		if(StringHelper.isEmpty(timeStamp)){
			throw new MessageException("时间戳缺失");
		}
		if(StringHelper.isEmpty(sign)){
			throw new MessageException("签名缺失");
		}
		if(StringHelper.isAnyEmpty(newPassword)){
			throw new MessageException("新密码缺失");
		}
		return IdmSupport.changeUserPassword(loginName, newPassword, sign, idmKey, timeStamp);
	}

}
