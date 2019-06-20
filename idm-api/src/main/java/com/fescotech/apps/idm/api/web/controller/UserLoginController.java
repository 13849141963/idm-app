package com.fescotech.apps.idm.api.web.controller;
import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.UserCheckDto;
import com.fescotech.apps.idm.api.util.CheckSysName;
import com.fescotech.apps.idm.api.web.support.IdmSupport;
import com.fescotech.apps.idm.base.domain.BaseSys;
import com.fescotech.common.util.StringHelper;
/**
 * 用户登录验证
 */
public class UserLoginController extends JsonControllerSupport<UserCheckDto> {
	@Override
	protected Object doHandle(UserCheckDto user) {
		String idmKey = user.getIdmKey();
		String loginName = user.getLoginName();
		String timeStamp = user.getTimeStamp();
		String sign = user.getSign();
		BaseSys bSys = CheckSysName.doHandle(user);	
		if(StringHelper.isEmpty(loginName)){
			throw new MessageException("登录名缺失");
		}
		if(StringHelper.isEmpty(timeStamp)){
			throw new MessageException("时间戳缺失");
		}
		if(StringHelper.isEmpty(sign)){
			throw new MessageException("签名缺失");
		}
		return IdmSupport.checkLogin(loginName, sign, idmKey, timeStamp, bSys.getSysId());
	}

}