package com.fescotech.apps.idm.api.web.controller;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.RoleQueryByUserIdDto;
import com.fescotech.apps.idm.api.util.CheckSysName;
import com.fescotech.apps.idm.api.web.support.IdmSupport;
import com.fescotech.apps.idm.base.domain.BaseSys;
import com.fescotech.common.util.StringHelper;

/**
 * 根据用户ID查询角色数据集接口
 * @author:lzl
 * @time:2017年9月13日 下午3:43:37
 */
public class RoleQueryByUserIdController extends JsonControllerSupport<RoleQueryByUserIdDto>{
	@Override
	protected Object doHandle(RoleQueryByUserIdDto user) {
		String userId = user.getUserId();
		String timeStamp = user.getTimeStamp();
		String sign = user.getSign();
		BaseSys bs = (BaseSys)CheckSysName.doHandle(user);
		if(StringHelper.isEmpty(userId)){
			throw new MessageException("用户ID缺失");
		}
		if(StringHelper.isEmpty(timeStamp)){
			throw new MessageException("时间戳缺失");
		}
		if(StringHelper.isEmpty(sign)){
			throw new MessageException("签名缺失");
		}
		
		if(StringHelper.isEmpty(user.getIden())){
			return IdmSupport.queryRoleInforByUserId(userId, sign, user.getIdmKey(), timeStamp);
		}else{
			return IdmSupport.queryRoleInforByUserId(userId, sign, user.getIdmKey(), timeStamp, bs);
		}
		
	}
	
}
