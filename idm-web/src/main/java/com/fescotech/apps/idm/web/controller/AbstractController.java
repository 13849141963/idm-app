package com.fescotech.apps.idm.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.web.util.ShiroUtils;
/**
 * Controller公共组件（用于权限控制）
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUser  getUser() {
		return ShiroUtils.getUserEntity();
	}
/*	protected BaseUser  getUser() {
		return ShiroUtils.getUserEntity();
	}*/

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
