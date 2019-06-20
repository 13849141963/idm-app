package com.fescotech.apps.idm.web.util;

import org.apache.shiro.SecurityUtils;

import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
import com.fescotech.apps.idm.bootstrap.ServiceManager;

public class SysUserUtils {

	public static SysUserVo getDbSysUser() {
		SysUser cacheSysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
		SysUserVo sysUser = new SysUserVo();
		if (cacheSysUser != null) 
			sysUser = ServiceManager.getSysUserService().queryObject(cacheSysUser.getUserId());
		return sysUser;
	}
	
	public static SysUserVo getDbSysUserByUserId(Long userId) {
		SysUserVo sysUser = ServiceManager.getSysUserService().queryObject(userId);
		return sysUser;
	}
	
}
