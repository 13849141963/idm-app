package com.fescotech.apps.idm.web.controller.base;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
import com.fescotech.apps.idm.base.biz.service.IBaseDictService;
import com.fescotech.apps.idm.base.biz.service.IBaseOrganizationService;
import com.fescotech.apps.idm.base.biz.service.IBaseRoleService;
import com.fescotech.apps.idm.base.biz.service.IBaseUserRoleService;
import com.fescotech.apps.idm.base.biz.service.IBaseUserService;
import com.fescotech.apps.idm.base.domain.BaseDict;
import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.BaseUser;
import com.fescotech.apps.idm.base.domain.BaseUserRole;
import com.fescotech.apps.idm.common.BizConstants;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.controller.AbstractController;
import com.fescotech.apps.idm.web.dto.PageDto;
import com.fescotech.apps.idm.web.util.ShiroUtils;
import com.fescotech.apps.idm.web.util.SysUserUtils;

@RestController
@RequestMapping("/base/baseUser")
public class BaseUserController extends AbstractController {

	@Autowired
	private IBaseUserService baseUserService;

	@Autowired
	private IBaseDictService baseDictService;

	@Autowired
	private IBaseOrganizationService baseOrganizationService;

	@Autowired
	private IBaseRoleService baseRoleService;

	@Autowired
	private IBaseUserRoleService baseUserRoleService;



	@RequestMapping("/list")
	@RequiresPermissions("base:baseUser:list")
	public R list(PageDto dto, @RequestParam(value = "orgId", required = false) Long orgId,
			@RequestParam(value = "userName", required = false) String userName) {
		Integer page=dto.getPage();
		Integer limit=dto.getLimit();
		userName = encodingUserName(userName);
		Map<String, Object> map = buildQuerylistMap(dto, orgId, userName, page, limit);
		// 查询列表数据
		int total = baseUserService.queryTotal(map);
		if(total == 0){
			return R.ok().put("page",null);
		}
		List<BaseUser> userList = baseUserService.queryUserList(map);
		Pager pageUtil = new Pager(userList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	private String encodingUserName(String userName) {
		if(userName!=null){
			try {
				userName=new String(userName.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new MessageException(e.getMessage());
			}
		}
		return userName;
	}

	private Map<String, Object> buildQuerylistMap(PageDto dto, Long orgId, String userName, Integer page,
			Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		//排序用的参数
		if(dto.getSidx()!=null){
			String odName=dto.getSidx();
			if("loginName".equals(odName)){
				map.put("orderBy", "LOGIN_NAME");
			}
			if("userName".equals(odName)){
				map.put("orderBy", "user_Name");
			}
			if("sex".equals(odName)){
				map.put("orderBy", "sex");
			}
			if("address".equals(odName)){
				map.put("orderBy", "ADDRESS");
			}
			if("phone".equals(odName)){
				map.put("orderBy", "phone");
			}
			if("email".equals(odName)){
				map.put("orderBy", "email");
			}
			if("userStatus".equals(odName)){
				map.put("orderBy", "USER_STATUS");
			}
		} 
		if (!map.containsKey("orderBy")) {
			map.put("orderBy", "create_Time");
			map.put("orderFlag", "DESC");
		} else {
			map.put("orderFlag", dto.getOrder());
		}
		
		map.put("start", (page - 1) * limit);
		map.put("end", page * limit);
		map.put("userName", userName);
		// 查询子orgId是所点击的orgId
		List<Long> orgIds = findChildOrgId(orgId);
		map.put("orgIds", orgIds);
		return map;
	}

	private List<Long> findChildOrgId(Long orgId) {
		List<BaseOrganization> orgList = new ArrayList<BaseOrganization>();
		if (orgId == null || orgId == 0) {
			BaseOrganization org = new BaseOrganization();
			orgList = baseOrganizationService.queryAllOrg(org);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);
			orgList = baseOrganizationService.queryAllOrgList(map);
		}
		List<Long> orgIds = new ArrayList<Long>();
		for (BaseOrganization org : orgList) {
			orgIds.add(org.getOrgId());
		}
		return orgIds;
	}

	@RequestMapping("/info/{userId}")
	@RequiresPermissions("base:baseUser:info")
	public R info(@PathVariable("userId") Long userId) {
		BaseUser user = baseUserService.queryUserById(userId);
		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:baseUser:save")
	public R save(@RequestBody BaseUser user) {
		if (StringUtils.isBlank(user.getUserPwd())) {
			user.setUserPwd("123456");
		}
		try {
			// 判断loginname是否重复
			String loginName = user.getLoginName();
			BaseUser bUser = baseUserService.queryUserByLoginName(loginName);
			if (bUser != null) {
				throw new ResException("登录名不能重复");
			}
			user.setCreatorName(getUser().getUsername());
			user.setCreator(getUserId());
			//user.setCreatorOrg(getUser().getOrgId());
			user.setCreateTime(new Date());
			// 参数校验
			verifyForm(user);
			baseUserService.saveBaseUser(user);
		} catch (ResException e) {
			return R.error(e.getMessage());
		}
		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:baseUser:update")
	public R update(@RequestBody BaseUser user) {
		user.setOperTime(new Date());
		// 参数校验
		try {
			verifyForm(user);
			baseUserService.updateBaseUser(user);
		} catch (ResException e) {
			return R.error(e.getMessage());
		}
		return R.ok();
	}

	// 参数校验
	private void verifyForm(BaseUser user) {
		if (StringUtils.isBlank(user.getLoginName())) {
			throw new ResException("登陆用户名不能为空");
		}
		if (StringUtils.isBlank(user.getUserName())) {
			throw new ResException("用户名不能为空");
		}
		if (user.getOrgId() == null) {
			throw new ResException("机构不能为空");
		}
		if (!StringUtils.isBlank(user.getUserPwd())) {
			try {
				user.setUserPwd(new Sha256Hash(user.getUserPwd()).toHex());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (user.getUserStatus() == null) {
			user.setUserStatus(1);
		}
		user.setOperator(getUserId());
		user.setOperatorName(getUser().getUsername());
		//user.setOperatorOrg(getUser().getOrgId());
		BaseOrganization org = baseOrganizationService.queryObject(user.getOrgId());
		user.setCorpId(org.getCorpId());

	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:baseUser:delete")
	public R delete(@RequestBody Long[] userIds) {
		SysUserVo sysUser = SysUserUtils.getDbSysUser();
		for (Long userId : userIds) {
			BaseUser baseUser = baseUserService.queryUserById(userId);
			if (!baseUser.getCreator().toString().equals(sysUser.getUserId().toString())) {
				return R.error("存在非当前登录账号创建的用户，不能删除");
			}
			BaseUserRole bur = new BaseUserRole();
			bur.setUserId(userId);
			List<BaseUserRole> urList = baseUserRoleService.getUserRoleList(bur);
			if (urList.size() != 0) {
				return R.error("请先取消该用户下的授权再删除用户");
			}
		}
		baseUserService.deleteBatch(userIds);
		return R.ok();
	}

	/**
	 * 机构树
	 */
	/*@RequestMapping("/selectOrg")
	@RequiresPermissions("base:baseUser:selectOrg")
	public R selectOrg() {
		// 查询列表数据
		List<BaseOrganizationVo> orgList = baseOrganizationService.queryTreeList();

		// 添加顶级菜单
		BaseOrganizationVo root = new BaseOrganizationVo();
		root.setOrgId(0L);
		root.setOrgName("一级机构");
		root.setParentId(-1L);
		root.setOpen(true);
		orgList.add(root);

		return R.ok().put("orgList", orgList);
	}*/

	

	/**
	 * 性别列表
	 */
	@RequestMapping("/selectSex")
	public R selectSex() {
		// 查询列表数据
		List<BaseDict> list = baseDictService.queryBaseDictItemList("SEX");

		return R.ok().put("list", list);
	}

	/**
	 * 用户类型列表
	 */
	@RequestMapping("/selectType")
	public R selectType() {
		// 查询列表数据
		List<BaseDict> list = baseDictService.queryBaseDictItemList("USER_TYPE");

		return R.ok().put("list", list);
	}

	/**
	 * 用户状态列表
	 */
	@RequestMapping("/selectStatus")
	public R selectStatus() {
		// 查询列表数据
		List<BaseDict> list = baseDictService.queryBaseDictItemList("USER_STATUS");
		return R.ok().put("list", list);
	}

	
	/**
	 * 修改用户状态
	 */
	@RequestMapping("/open")
	@RequiresPermissions("base:baseUser:open")
	public R open(@RequestBody Long userId) {
		try {
			baseUserService.changeState(userId, BizConstants.USER_STATUS_OPEN);
		} catch (ResException e) {
			return R.error(e.getMessage());
		}
		return R.ok();
	}

	@RequestMapping("/close")
	@RequiresPermissions("base:baseUser:close")
	public R close(@RequestBody Long userId) {
		try {
			baseUserService.changeState(userId, BizConstants.USER_STATUS_CLOSED);
		} catch (ResException e) {
			return R.error(e.getMessage());
		}
		return R.ok();
	}

	@RequestMapping("/clock")
	@RequiresPermissions("base:baseUser:clock")
	public R clock(@RequestBody Long userId) {
		try {
			baseUserService.changeState(userId, BizConstants.USER_STATUS_LOCKED);
		} catch (ResException e) {
			return R.error(e.getMessage());
		}
		return R.ok();
	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/selectRole")
	public R selectRole() {
		// 查询列表数据
		List<BaseRole> list = baseRoleService.queryBaseRole(new BaseRole());
		return R.ok().put("list", list);
	}

	/**
	 * 取消角色授权信息
	 */
	@RequestMapping("/cancelUserRole")
	@RequiresPermissions("base:baseUser:cancelUserRole")
	public R cancelUserRole(@RequestBody Long[] baseUserRoleIds) {
		baseUserRoleService.deleteUserRole(baseUserRoleIds);
		return R.ok();
	}

	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/password")
	public R password(String password, String newPassword, String newAgainPassword) {
		if (StringUtils.isBlank(newPassword)) {
			return R.error("新密码不为能空");
		}
		if (!newPassword.equals(newAgainPassword)) {
			return R.error("两次密码不一样");
		}
		// sha256加密
		password = new Sha256Hash(password).toHex();
		// sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();

		newAgainPassword = new Sha256Hash(newAgainPassword).toHex();
		// 更新密码
		int count = baseUserService.updatePassword(getUserId(), password, newPassword);
		if (count == 0) {
			return R.error("原密码不正确");
		}

		// 退出
		ShiroUtils.logout();

		return R.ok();
	}

	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/changePwd")
	public R changePwd(@RequestBody BaseUser baseUser) {
		String newPassword = baseUser.getUserPwd();
		if (StringUtils.isBlank(newPassword)) {
			return R.error("新密码不为能空");
		}
		// sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();
		BaseUser buser = baseUserService.queryUserById(baseUser.getUserId());

		// 更新密码
		int count = baseUserService.updatePassword(baseUser.getUserId(), buser.getUserPwd(), newPassword);
		if (count == 0) {
			return R.error("原密码不正确");
		}
		return R.ok();
	}

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/userInfo")
	public R userInfo() {
		SysUser user = getUser();
		return R.ok().put("user", user);
	}

	/**
	 * 获取用户的角色信息
	 */
	@RequestMapping("/userRoleList/{userId}")
	@RequiresPermissions("base:baseUser:userRole")
	public R userRoleInfo(Integer page, Integer limit, @PathVariable("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit);
		map.put("end", page * limit);

		map.put("userId", userId);

		List<BaseUserRole> urList = baseUserRoleService.getUserRoleList(map);
		for (BaseUserRole ur : urList) {
			BaseRole baseRole = new BaseRole();
			baseRole.setRoleId(ur.getRoleId());
			baseRole = baseRoleService.findBaseRole(baseRole);
			ur.setRoleName(baseRole.getRoleName());
		}

		BaseUserRole bur = new BaseUserRole();
		bur.setUserId(userId);
		int total = baseUserRoleService.queryTotal(bur);

		Pager pageUtil = new Pager(urList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 系统应用列表
	 */
	/*@RequestMapping("/selectSys")
	public R selectSys() {
		// 查询列表数据
		List<BaseSys> list = baseSysService.queryList(new BaseSys());
		return R.ok().put("list", list);
	}*/

	/**
	 * 给用户添加角色
	 */
	@RequestMapping("/addRole")
	public R addRole(@RequestBody BaseUserRole userRole) {
		userRole.setUserRoleState(1);
		userRole.setCreateTime(new Date());
		baseUserRoleService.saveUserRole(userRole);
		return R.ok();
	}

	/**
	 * 获取系统的角色信息（已失效）
	 */
	/*@RequestMapping("/sysRoleList/{userId}")
	public R sysRoleList(Integer page, Integer limit, @PathVariable("userId") Long userId,
			@RequestParam(value = "sysId", required = false) Long sysId) {
		Map<String, Object> map = new HashMap<String, Object>();
		BaseUserRole bur = new BaseUserRole();
		bur.setUserId(userId);
		BaseRole role = new BaseRole();
		map.put("start", (page - 1) * limit);
		map.put("end", page * limit);
		if (sysId != null) {
			map.put("sysId", sysId);
			role.setSysId(Integer.valueOf(sysId.toString()));
		}
		List<BaseUserRole> urList = baseUserRoleService.getUserRoleListByUr(bur);
		List<BaseRoleVo> roleList = baseRoleService.queryList(map);
		for (int i = 0; i < roleList.size(); i++) {
			for (BaseUserRole ur : urList) {
				if (ur.getRoleId().equals(roleList.get(i).getRoleId())) {
					roleList.remove(roleList.get(i));
				}
			}
		}
		int total = baseRoleService.queryBaseRoleCount(role);

		Pager pageUtil = new Pager(roleList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}*/

	

}
