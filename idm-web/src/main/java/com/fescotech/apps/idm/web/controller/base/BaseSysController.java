package com.fescotech.apps.idm.web.controller.base;

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

import com.fescotech.apps.idm.admin.biz.service.ISysUserService;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
import com.fescotech.apps.idm.base.biz.service.IBaseRoleService;
import com.fescotech.apps.idm.base.biz.service.IBaseSysService;
import com.fescotech.apps.idm.base.biz.service.IBaseUserRoleService;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.BaseSys;
import com.fescotech.apps.idm.base.domain.BaseUserRole;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.util.ResException;
import com.fescotech.apps.idm.web.controller.AbstractController;
import com.fescotech.apps.idm.web.dto.PageDto;
import com.fescotech.apps.idm.web.util.SysUserUtils;


@RestController
@RequestMapping("/base/baseSys")
public class BaseSysController extends AbstractController{

	@Autowired
	private  IBaseSysService baseSysService;
	@Autowired
	private IBaseRoleService baseRoleService;
	@Autowired
	private IBaseUserRoleService baseUserRoleService;
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 所有应用列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("base:baseSys:list")
	public R list(PageDto dto){
		Integer page=dto.getPage();
		Integer limit=dto.getLimit();
		
		Map<String, Object> map = new HashMap<String, Object>();
		//排序用的参数
		if(dto.getSidx()!=null){
			String odName=dto.getSidx();
			if("alias".equals(odName)){
				map.put("orderBy", "alias");
			}
			if("sysName".equals(odName)){
				map.put("orderBy", "sys_Name");
			}
			if("sysUrl".equals(odName)){
				map.put("orderBy", "sys_Url");
			}
			if("remark".equals(odName)){
				map.put("orderBy", "remark");
			}
			if("openFlag".equals(odName)){
				map.put("orderBy", "open_Flag");
			}
			if("operatorName".equals(odName)){
				map.put("orderBy", "operator_Name");
			}
			if("operTime".equals(odName)){
				map.put("orderBy", "oper_Time");
			}
		}	
		map.put("orderFlag", dto.getOrder());
		
		map.put("start", (page - 1) * limit);
		map.put("end", page*limit);	
		SysUserVo sysUser = SysUserUtils.getDbSysUser();
		List<BaseSys> resultAppList = new ArrayList<BaseSys>();
		List<BaseSys> appList = baseSysService.queryList(map);
		String[] sysIds = sysUser.getSysId().split(",");
		for (int i = 0; i < sysIds.length; i++) {
			for (int j = 0; j < appList.size(); j++) {
				if (sysIds[i].equals(appList.get(j).getSysId().toString())) {
					resultAppList.add(appList.get(j));
				}
			}
		}
		//查询列表数据
		int total = resultAppList.size();
		if(total==0){
			return R.ok().put("page",null);
		}
		Pager pageUtil = new Pager(resultAppList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 应用信息
	 */
	@RequestMapping("/info/{sysId}")
	@RequiresPermissions("base:baseSys:info")
	public R info(@PathVariable("sysId") Long sysId){
		BaseSys sys = baseSysService.queryObject(sysId);
		
		return R.ok().put("sys", sys);
	}
	
	/**
	 * 保存app
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:baseSys:save")
	public R save(@RequestBody BaseSys sys){
		try{
			//参数校验
			verifyForm(sys);
			BaseSys s=new BaseSys();
			s.setSysName(sys.getSysName());
			BaseSys bs=baseSysService.loadBaseSys(s);
			if(bs!=null){
				throw new ResException("应用名不能重复");
			}
			SysUserVo sysUser = SysUserUtils.getDbSysUser();
			sys.setCreator(sysUser.getUserId());
			sys.setCreatorName(sysUser.getUsername());
			baseSysService.save(sys);
			
			SysUserVo user = new SysUserVo();
			user.setUserId(sysUser.getUserId());
			String sysId = sysUser.getSysId();
			if (sysId != null && !sysId.equals("")) {
				user.setSysId(sysId+","+sys.getSysId().toString());
			} else {
				user.setSysId(sys.getSysId().toString());
			}
			user.setUsername(sysUser.getUsername());
			user.setPassword(sysUser.getPassword());
			user.setEmail(sysUser.getEmail());
			user.setMobile(sysUser.getMobile());
			user.setStatus(sysUser.getStatus());
			user.setCreateTime(sysUser.getCreateTime());
			sysUserService.update(user);
		}catch(ResException re){
			return R.error(re.getMsg());
		}
		return R.ok();
	}
	
	/**
	 * 修改app
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:baseSys:update")
	public R update(@RequestBody BaseSys sys){
		//参数校验
		verifyForm(sys);
		baseSysService.update(sys);
		return R.ok();
	}


	/**
	 * 删除app
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:baseSys:delete")
	public R delete(@RequestBody Long[] sysIds){
		List<BaseRole> roleList=new ArrayList<BaseRole>();
		for(Long sysId:sysIds){
			if (sysId != null) {
				BaseRole role = new BaseRole();
				role.setSysId(Integer.valueOf(sysId.toString()));
				roleList.addAll( baseRoleService.queryBaseRole(role));
			}
		}
		if(roleList.size()>0){
			return R.error("请删除该应用下的角色后再删除应用！");
		}
		baseSysService.deleteBatch(sysIds);
		return R.ok();
	}
	
	/**
	 * 修改app状态
	 */
	@RequestMapping("/changeState")
	@RequiresPermissions("base:baseSys:changeState")
	public R changeState(@RequestBody Long[] sysIds){
		baseSysService.changeState(sysIds);
		
		return R.ok();
	}
	
	
	private void verifyForm(BaseSys sys) {
		if(StringUtils.isBlank(sys.getSysName())){
			throw new ResException("应用名称不能为空");
		}
		if(StringUtils.isBlank(sys.getSysUrl())){
			throw new ResException("应用地址不能为空");
		}
		if(sys.getOpenFlag()==null){
			sys.setOpenFlag(1);
		}
		
		sys.setOperatorName(getUser().getUsername());
		sys.setOperTime(new Date());
	}
	
	@RequestMapping("/selectSys")
	public R selectSys() {
		// 查询列表数据
		BaseSys baseSys = new BaseSys();
		List<BaseSys> resultAppList = new ArrayList<BaseSys>();
		List<BaseSys> list = baseSysService.queryList(baseSys);
		return R.ok().put("list", list);
	}
	
	/**
	 * 获取系统的角色信息
	 */
	@RequestMapping("/sysRole/{userId}")
	public R sysRole(@PathVariable("userId") Long userId,
			@RequestParam(value = "sysId", required = false) Long sysId) {
		BaseUserRole bur = new BaseUserRole();
		bur.setUserId(userId);
		BaseRole role = new BaseRole();
		
		if (sysId != null) {
			role.setSysId(Integer.valueOf(sysId.toString()));
		}else{
			return R.ok().put("list", null);
		}
		List<BaseUserRole> urList = baseUserRoleService.getUserRoleListByUr(bur);
		List<BaseRole> roleList = baseRoleService.queryBaseRole(role);
		for (int i = 0; i < roleList.size(); i++) {
			for (BaseUserRole ur : urList) {
				if (ur.getRoleId().equals(roleList.get(i).getRoleId())) {
					roleList.remove(roleList.get(i));
				}
			}
		}
		return R.ok().put("list", roleList);
	}
	
	
}
