package com.fescotech.apps.idm.web.controller.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
import com.fescotech.apps.idm.base.biz.service.IBaseDictService;
import com.fescotech.apps.idm.base.biz.service.IBaseRoleService;
import com.fescotech.apps.idm.base.biz.service.IBaseSysService;
import com.fescotech.apps.idm.base.biz.service.IBaseUserRoleService;
import com.fescotech.apps.idm.base.domain.BaseDict;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.BaseRoleStatus;
import com.fescotech.apps.idm.base.domain.BaseRoleSys;
import com.fescotech.apps.idm.base.domain.BaseSys;
import com.fescotech.apps.idm.base.domain.BaseUserRole;
import com.fescotech.apps.idm.base.domain.vo.BaseRoleVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.web.controller.AbstractController;
import com.fescotech.apps.idm.web.util.SysUserUtils;

/**
 * 角色管理逻辑处理类
 * @author:lzl
 * @time:2017年6月12日 下午5:26:09
 */
@RestController
@RequestMapping("/base/role")
public class BaseRoleController extends AbstractController {

	@Autowired
	private IBaseRoleService baseRoleService;
	@Autowired
	private IBaseDictService baseDictService;
	@Autowired
	private IBaseSysService baseSysService;
	@Autowired
	private IBaseUserRoleService baseUserRoleService;
	
	/**
	 * 获取角色列表
	 * @param page
	 * @param limit
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/list")
	@RequiresPermissions("base:role:list")
	public R list(Integer page, Integer limit,String order,String sidx, @RequestParam(value="roleName", required=false) String roleName, 
			@RequestParam(value="sysId", required=false) String sysId) throws UnsupportedEncodingException{
		Map<String, Object> map = new HashMap<String, Object>();
		BaseRole baseRole = new BaseRole();
		map.put("start", (page - 1) * limit);
		map.put("end", page*limit);
		if (sidx != null && !sidx.equals("")) {
			if (sidx.equals("roleName")) {
				sidx = "ROLE_NAME";
			} else if (sidx.equals("roleCode")) {
				sidx = "ROLE_CODE";
			} else if (sidx.equals("buildTime")) {
				sidx = "BUILD_TIME";
			}
			map.put("orderBy", sidx);
			map.put("orderFlag", order);
		}
		if (roleName != null && !roleName.equals("")) {
			roleName = URLDecoder.decode(roleName,"utf-8");
			map.put("roleName", roleName);
			baseRole.setRoleName(roleName);
		}
		if (sysId != null && !sysId.equals("") && !sysId.equals("-1")) {
			map.put("sysId", sysId);
			baseRole.setSysId(Integer.valueOf(sysId));
		}
		List<BaseRoleVo> resutList = new ArrayList<BaseRoleVo>();
		SysUserVo sysUser = SysUserUtils.getDbSysUser();
		String[] sysIds = sysUser.getSysId().split(",");
		int total = baseRoleService.queryBaseRoleCount(baseRole);
		if (total > 0) {
			List<BaseRoleVo> list = baseRoleService.queryList(map);
			for (int j = 0; j < sysIds.length; j++) {
				for (int i = 0; i < list.size(); i++) {
					if (sysIds[j].equals(list.get(i).getSysId().toString())) {
						BaseDict baseDict = new BaseDict();
						baseDict.setDictType("ROLE_STATUS");
						baseDict.setDictCode(list.get(i).getRoleStatus().toString());
						list.get(i).setRoleStatusName(baseRoleService.queryRoleStatusName(baseDict));//角色状态描述
						list.get(i).setRoleSysName(baseSysService.queryObject(Long.valueOf(list.get(i).getSysId().toString())).getSysName());//角色所属系统名称
						resutList.add(list.get(i));
					}
				}
			}
			total = resutList.size();
			Pager pageUtil = new Pager(resutList, total, limit, page);
			return R.ok().put("page", pageUtil);
		} else {
			return R.ok();
		}
	}
	
	/**
	 * 保存角色
	 * @throws ParseException 
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:role:save")
	public R save(@RequestBody BaseRoleVo role) throws ParseException{
		if(StringUtils.isBlank(role.getRoleName())){
			return R.error("角色名称不能为空");
		}
		if (role.getSysId() == null || role.getSysId() == -1) {
			return R.error("所属系统不能为空");
		}
		if (role.getRoleStatus() == null) {
			return R.error("角色状态不能为空");
		}
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		role.setBuildTime(sdf.parse(sdf.format(now)));
		role.setOperTime(sdf.parse(sdf.format(now)));
		SysUserVo baseUser = SysUserUtils.getDbSysUser();
		role.setCreator(baseUser.getUserId());
		role.setCreatorName(baseUser.getUsername());
		baseRoleService.insert(role);
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		Long[] deleteRoleIds = new Long[roleIds.length];
		StringBuffer deleteMsg = new StringBuffer();
		List<BaseUserRole> hasRelatedUsers = new ArrayList<BaseUserRole>();
		for (int i = 0; i < roleIds.length; i++) {//关联用户的角色不许删除
			BaseUserRole userRole =  new BaseUserRole();
			userRole.setRoleId(roleIds[i]);
			List<BaseUserRole> baseUserRoleList = baseUserRoleService.getUserRoleList(userRole);
			if (baseUserRoleList != null && baseUserRoleList.size() > 0) {
				hasRelatedUsers.add(baseUserRoleList.get(0));
			} else {
				deleteRoleIds[i] = roleIds[i];
			}
		}
		if (hasRelatedUsers != null && hasRelatedUsers.size() > 0) {
			deleteMsg.append("角色：");
			for (int i = 0; i < hasRelatedUsers.size(); i++) {
				BaseRoleVo baseRoleVo = baseRoleService.loadBaseRole(hasRelatedUsers.get(i).getRoleId());
				deleteMsg.append(baseRoleVo.getRoleName()+"，");
			}
			deleteMsg.append("已经关联用户不允许删除");
		}
		baseRoleService.deleteBatch(deleteRoleIds);
		baseRoleService.deleteRoleFunction(deleteRoleIds);
		return R.ok().ele("deleteMsg", deleteMsg);
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:role:update")
	public R update(@RequestBody BaseRoleVo role){
		if(StringUtils.isBlank(role.getRoleName())){
			return R.error("角色名称不能为空");
		}
		if (role.getSysId() == null) {
			return R.error("系统ID不能为空");
		}
		baseRoleService.update(role);
		return R.ok();
	}
	
	/**
	 * 查询角色信息（修改操作）
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("base:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		BaseRoleVo role = new BaseRoleVo();
		if (roleId != null) {
			role = baseRoleService.loadBaseRole(roleId);
		}
		List<BaseDict> baseDictList = baseDictService.queryBaseDictItemList("ROLE_STATUS");
		if (baseDictList != null && baseDictList.size() > 0) {
			List<BaseRoleStatus> baseRoleStatusList = new ArrayList<BaseRoleStatus>();
			for (int i = 0; i < baseDictList.size(); i++) {
				BaseRoleStatus baseRoleStatus = new BaseRoleStatus();
				baseRoleStatus.setRoleStatueCode(baseDictList.get(i).getDictCode());
				baseRoleStatus.setRoleStatueName(baseDictList.get(i).getDictName());
				baseRoleStatusList.add(baseRoleStatus);
			}
			role.setBaseRoleStatusList(baseRoleStatusList);
		}
		SysUserVo sysUser = SysUserUtils.getDbSysUser();
		BaseSys baseSys = new BaseSys();
		List<BaseSys> baseSysList = new ArrayList<BaseSys>();
		List<BaseSys> tempSysList = baseSysService.queryList(baseSys);//系统集合
		String[] sysIds = sysUser.getSysId().split(",");
		for (int i = 0; i < sysIds.length; i++) {
			for (int j = 0; j < tempSysList.size(); j++) {
				if (sysIds[i].equals(tempSysList.get(j).getSysId().toString())) {
					baseSysList.add(tempSysList.get(j));
					if (sysIds[i].equals(role.getSysId().toString())) {
						role.setRoleSysName(tempSysList.get(j).getSysName());
					}
				}
			}
		}
//		if (baseSysList != null && baseSysList.size() > 0) {
//			List<BaseRoleSys> baseRoleSysList = new ArrayList<BaseRoleSys>();
//			for (int i = 0; i < baseSysList.size(); i++) {
//				BaseRoleSys baseRoleSys = new BaseRoleSys();
//				baseRoleSys.setRoleSysId(Integer.valueOf(baseSysList.get(i).getSysId().toString()));
//				baseRoleSys.setRoleSysName(baseSysList.get(i).getSysName());
//				baseRoleSysList.add(baseRoleSys);
//			}
//			role.setBaseRoleSysList(baseRoleSysList);
//		}
		return R.ok().put("role", role);
	}
	
	/**
	 * 获取字典信息(新增操作)
	 */
	@RequestMapping("/dict")
	@RequiresPermissions("base:role:dict")
	public R dict(){
		BaseRoleVo role = new BaseRoleVo();
		List<BaseDict> baseDictList = baseDictService.queryBaseDictItemList("ROLE_STATUS");//字典表查询角色状态
		if (baseDictList != null && baseDictList.size() > 0) {
			List<BaseRoleStatus> baseRoleStatusList = new ArrayList<BaseRoleStatus>();
			for (int i = 0; i < baseDictList.size(); i++) {
				BaseRoleStatus baseRoleStatus = new BaseRoleStatus();
				baseRoleStatus.setRoleStatueCode(baseDictList.get(i).getDictCode());
				baseRoleStatus.setRoleStatueName(baseDictList.get(i).getDictName());
				baseRoleStatusList.add(baseRoleStatus);
			}
			role.setBaseRoleStatusList(baseRoleStatusList);
		}
		SysUserVo sysUser = SysUserUtils.getDbSysUser();
		BaseSys baseSys = new BaseSys();
		List<BaseSys> baseSysList = new ArrayList<BaseSys>();
		List<BaseSys> tempSysList = baseSysService.queryList(baseSys);//系统集合
		String[] sysIds = sysUser.getSysId().split(",");
		for (int i = 0; i < sysIds.length; i++) {
			for (int j = 0; j < tempSysList.size(); j++) {
				if (sysIds[i].equals(tempSysList.get(j).getSysId().toString())) {
					baseSysList.add(tempSysList.get(j));
				}
			}
		}
		if (baseSysList != null && baseSysList.size() > 0) {
			List<BaseRoleSys> baseRoleSysList = new ArrayList<BaseRoleSys>();
			BaseRoleSys defaultValue = new BaseRoleSys();
			defaultValue.setRoleSysId(-1);
			defaultValue.setRoleSysName("请选择");
			baseRoleSysList.add(defaultValue);
			for (int i = 0; i < baseSysList.size(); i++) {
				BaseRoleSys baseRoleSys = new BaseRoleSys();
				baseRoleSys.setRoleSysId(Integer.valueOf(baseSysList.get(i).getSysId().toString()));
				baseRoleSys.setRoleSysName(baseSysList.get(i).getSysName());
				baseRoleSysList.add(baseRoleSys);
			}
			role.setBaseRoleSysList(baseRoleSysList);
		}
		return R.ok().put("role", role);
	}
	
	/**
	 * 角色的已授权信息
	 */
	@RequestMapping("/func/{roleId}")
	@RequiresPermissions("base:role:func")
	public R func(@PathVariable("roleId") Long roleId){
		BaseRoleVo role = baseRoleService.loadBaseRole(roleId);
		if (role != null) {
			List<Long> functionIdList = baseRoleService.queryFuncIdList(roleId);
			List<String> sysIdAndFunctionId = new ArrayList<String>();
			for (int i = 0; i < functionIdList.size(); i++) {
				sysIdAndFunctionId.add(role.getSysId() + "_" + functionIdList.get(i).toString());
			}
			role.setFunctionIdList(sysIdAndFunctionId);
		}
		return R.ok().put("role", role);
	}
	
}
