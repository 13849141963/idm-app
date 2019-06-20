package com.fescotech.apps.idm.api.util;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.api.dto.CheckSysNameDto;
import com.fescotech.apps.idm.base.domain.BaseSys;
import com.fescotech.apps.idm.bootstrap.ServiceManager;
import com.fescotech.common.util.StringHelper;

public class CheckSysName{

	public static BaseSys doHandle(CheckSysNameDto sys) {
		if(StringHelper.isEmpty(sys.getIdmKey())){
			throw new MessageException("应用标识名缺失");
		}
		BaseSys bs=new BaseSys();
		bs.setAlias(sys.getIdmKey());//应用别名
		bs=ServiceManager.getBaseSysService().loadBaseSys(bs);
		if(bs!=null){
			if(bs.getOpenFlag().equals(2)){
				throw new MessageException("此应用尚未开启");
			}
			return bs;
		}
		throw new MessageException("查无此应用");
	}

}
