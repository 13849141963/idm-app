package com.fescotech.apps.idm.bootstrap;
import com.fescotech.apps.idm.admin.biz.service.ISysConfigService;
import com.fescotech.apps.idm.admin.biz.service.ISysMenuService;
import com.fescotech.apps.idm.admin.biz.service.ISysRoleService;
import com.fescotech.apps.idm.admin.biz.service.ISysUserService;
import com.fescotech.apps.idm.base.biz.service.*;
import com.fescotech.apps.idm.util.SpringContextUtils;
public class ServiceManager{	
 	/**
	 * 获取用户管理服务
	 * @return
	 */
	public static ISysUserService getSysUserService(){
		return (ISysUserService) SpringContextUtils.getBean("sysUserService");
	}
	
	public static ISysMenuService getSysMenuService(){
		return (ISysMenuService) SpringContextUtils.getBean("sysMenuService");
	}
		
	public static ISysRoleService getSysRoleService(){
		return (ISysRoleService) SpringContextUtils.getBean("sysRoleService");
	}
	
	public static ISysConfigService getSysConfigService(){
		return (ISysConfigService) SpringContextUtils.getBean("sysConfigService");
	}
	 
	public static IBaseUserService getBaseUserService(){
		return (IBaseUserService) SpringContextUtils.getBean("baseUserService");
	} 
	
	public static IBaseUserRoleService getBaseUserRoleService(){
		return (IBaseUserRoleService) SpringContextUtils.getBean("baseUserRoleService");
	} 
	
	public static IBaseRoleFunctionService getBaseRoleFunctionService(){
		return (IBaseRoleFunctionService) SpringContextUtils.getBean("baseRoleFunctionService");
	} 
	
	public static IBaseFunctionService getBaseFunctionService(){
		return (IBaseFunctionService) SpringContextUtils.getBean("baseFunctionService");
	} 
	
	public static IBaseSysService getBaseSysService(){
		return (IBaseSysService) SpringContextUtils.getBean("baseSysService");
	} 
	
	public static IBaseLogService getBaseLogService(){
		return (IBaseLogService) SpringContextUtils.getBean("baseLogService");
	}
	
	public static IApiLogService getApiLogService(){
		return (IApiLogService) SpringContextUtils.getBean("apiLogService");
	}
	
	public static IBaseOrganizationService getBaseOrganizationService(){
		return (IBaseOrganizationService) SpringContextUtils.getBean("baseOrganizationService");
	}
	
	public static IBaseRoleService getBaseRoleService(){
		return (IBaseRoleService) SpringContextUtils.getBean("baseRoleService");
	}

	public static IBaseCorpinfoService getBaseCorpinfoService(){
		return (IBaseCorpinfoService) SpringContextUtils.getBean("baseCorpinfoService");
	}
	
}
