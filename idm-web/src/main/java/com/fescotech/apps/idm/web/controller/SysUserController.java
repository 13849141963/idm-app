package com.fescotech.apps.idm.web.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fescotech.apps.idm.admin.biz.service.ISysUserService;
import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.web.util.ShiroUtils;
/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private ISysUserService sysUserService;
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit);
		map.put("end", page*limit);		
		//查询列表数据
		List<SysUser> userList = sysUserService.queryList(map);
		int total = sysUserService.queryTotal(map);		
		Pager pageUtil = new Pager(userList, total, limit, page);		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/password")
	public R password(String password, String newPassword, String newAgainPassword){
		if(StringUtils.isBlank(newPassword)){
			return R.error("新密码不为能空");
		}
		if (!newPassword.equals(newAgainPassword)) {
			return R.error("两次密码不一样");
		}
		
		//sha256加密
		password = new Sha256Hash(password).toHex();
		//sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();
				
		//更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(count == 0){
			return R.error("原密码不正确");
		}
		
		//退出
		ShiroUtils.logout();
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserVo  user = sysUserService.queryObject(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		ArrayList<Long> sysIdList = new ArrayList<Long>();
		if (user.getSysId() != null && !user.getSysId().equals("")) {
			String[] sysIds = user.getSysId().split(",");
			for (int i = 0; i < sysIds.length; i++) {
				sysIdList.add(Long.valueOf(sysIds[i]));
			}
			user.setSysIdList(sysIdList);
		}
		user.setPassword(null);
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserVo user){
		if(StringUtils.isBlank(user.getUsername())){
			return R.error("用户名不能为空");
		}
		if(StringUtils.isBlank(user.getPassword())){
			return R.error("密码不能为空");
		}
		List<Long> sysIdList = user.getSysIdList();
		StringBuffer sysId = new StringBuffer();
		if (sysIdList != null && sysIdList.size() >0) {
			for (int i = 0; i < sysIdList.size(); i++) {
				if (i < sysIdList.size() - 1) {
					sysId.append(sysIdList.get(i).toString() + ",");
				} else {
					sysId.append(sysIdList.get(i).toString());
				}
			}
			user.setSysId(sysId.toString());
		}
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserVo user){
		if(StringUtils.isBlank(user.getUsername())){
			return R.error("用户名不能为空");
		}
		List<Long> sysIdList = user.getSysIdList();
		StringBuffer sysIdAppend = new StringBuffer();
		if (sysIdList != null && sysIdList.size() >0) {
			for (int i = 0; i < sysIdList.size(); i++) {
				if (i < sysIdList.size() - 1) {
					sysIdAppend.append(sysIdList.get(i).toString() + ",");
				} else {
					sysIdAppend.append(sysIdList.get(i).toString());
				}
			}
		}
		user.setSysId(sysIdAppend.toString());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
