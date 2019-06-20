package com.fescotech.apps.idm.web.controller.base;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 系统页面视图
 */
@Controller
public class BasePageController {
	
	/**
	 * 角色管理列表界面
	 * @param url
	 * @return
	 */
	@RequestMapping("base/role.html")
	public String pageBaseRole(){
		return "base/role.html";
	}
	
	/**
	 * 角色管理新增/修改界面
	 * @param url
	 * @return
	 */
	@RequestMapping("base/role_add.html")
	public String pageBaseRoleAdd(){
		return "base/role_add.html";
	}
	
	/**
	 * 日志查询界面
	 * @return
	 */
	@RequestMapping("base/log.html")
	public String pageBaseLog(){
		return "base/log.html";
	}
	
	/**
	 * 日志查询界面
	 * @return
	 */
	@RequestMapping("base/apiLog.html")
	public String pageApiLog(){
		return "base/apiLog.html";
	}
	
	/**
	 * 角色管理授权界面
	 * @param url
	 * @return
	 */
	@RequestMapping("base/role_auth.html")
	public String pageBaseRoleAuth(){
		return "base/role_auth.html";
	}
	
	/**
	 * 权限管理列表界面
	 * @param url
	 * @return
	 */
	@RequestMapping("base/function.html")
	public String pageBaseFunction(){
		return "base/function.html";
	}
	
	/**
	 * 权限管理新增/修改界面
	 * @param url
	 * @return
	 */
	@RequestMapping("base/function_add.html")
	public String pageBaseFunctionAdd(){
		return "base/function_add.html";
	}
	
	//应用管理 修改页面
	@RequestMapping("base/baseSys_add.html")
	public String pageBaseSysAdd(){
		return "base/baseSys_add.html";
	}
	
	//应用管理的列表页面
	@RequestMapping("base/baseSys.html")
	public String pageBaseSys(){
		return "base/baseSys.html";
	}

	//数据集权限的列表页面
	@RequestMapping("base/baseData.html")
	public String pageBaseData(){
		return "base/baseData.html";
	}

	//数据集权限的列表页面
	@RequestMapping("base/baseData_add.html")
	public String pageBaseDataAdd(){
		return "base/baseData_add.html";
	}
	//添加用户权限集页面
	@RequestMapping("base/baseUserData.html")
	public String pageBaseUserDataAdd(){
		return "base/baseUserData.html";
	}

	//展示用户权限集页面
	@RequestMapping("base/baseUserData_userAll.html")
	public String pageBaseUserDataAll(){
		return "base/baseUserData_userAll.html";
	}


	//用户管理  的修改页面
	@RequestMapping("base/baseUser_add.html")
	public String pageBaseUserAdd(){
		return "base/baseUser_add.html";
	}
	//用户管理  的授权修改页面
	@RequestMapping("base/baseUser_role.html")
	public String pageBaseUserRole(){
		return "base/baseUser_role.html";
	}
	//用户管理  的授权修改页面
	@RequestMapping("base/baseUser_sysRole.html")
	public String pageBaseUserSysRole(){
		return "base/baseUser_sysRole.html";
	}
	//用户管理  的修改密码页面
	@RequestMapping("base/baseUser_changePwd.html")
	public String pageBaseUserChangePwd(){
		return "base/baseUser_changePwd.html";
	}
	
	//用户管理的 列表页面
	@RequestMapping("base/baseUser.html")
	public String pageBaseUser(){
		return "base/baseUser.html";
	}
	
	//公司管理  的修改页面
	@RequestMapping("base/corp_add.html")
	public String pageBaseCorpAdd(){
		return "base/corp_add.html";
	}
	
	//公司管理的 列表页面
	@RequestMapping("base/corp.html")
	public String pageBaseCorp(){
		return "base/corp.html";
	}
	
	//机构管理  的修改页面
	@RequestMapping("base/org_add.html")
	public String pageBaseOrgAdd(){
		return "base/org_add.html";
	}
	
	//机构管理的 列表页面
	@RequestMapping("base/org.html")
	public String pageBaseOrg(){
		return "base/org.html";
	}
	
	//
	@RequestMapping("base/baseDataCustomer.html")
	public String pageBaseDataCustomer(){
		return "base/baseDataCustomer.html";
	}

	//
	@RequestMapping("base/baseDataCustomer_add.html")
	public String pageBaseDataCustomerAdd(){
		return "base/baseDataCustomer_add.html";
	}
	//
	public String pageBaseDataCustomerUpdate(){
		return "base/baseDataCustomer_update.html";
	}
	//
	@RequestMapping("base/baseDataCustomer_tianjia.html")
	public String pageBaseDataCustomerTianJia(){
		return "base/baseDataCustomer_tianjia.html";
	}
	//
	@RequestMapping("base/baseDataCustomer_tianjiatree.html")
	public String pageBaseDataCustomer_tianjiatree(){
		return "base/baseDataCustomer_tianjiatree.html";
	}
}
