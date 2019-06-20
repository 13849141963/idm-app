package com.fescotech.apps.idm.admin.biz.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import com.fescotech.apps.idm.admin.biz.bo.ISysUserBo;
import com.fescotech.apps.idm.admin.biz.bo.ISysUserRoleBo;
import com.fescotech.apps.idm.admin.biz.service.ISysUserService;
import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
/**
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {
	private ISysUserBo sysUserBo;
	private ISysUserRoleBo sysUserRoleBo;

	@Override
	public List<String> queryAllPerms(Long userId) {
		  return sysUserBo.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		 return sysUserBo.queryAllMenuId(userId);
	}

	@Override
	public SysUser  queryByUserName(String username) {
		return sysUserBo.queryByUserName(username);
	}
	
	@Override
	public SysUserVo  queryObject(Long userId) {
		return sysUserBo.loadSysUser(userId);
	}

	@Override
	public List<SysUser> queryList(Map<String, Object> map){
		return sysUserBo.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserBo.queryTotal(map);
	}

	@Override
	public void save(SysUserVo user) {
		
		user.setCreateTime(new Date());
		//sha256加密
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserBo.insert(user);		
		//保存用户与角色关系
		sysUserRoleBo.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void update(SysUserVo user) {
		List<Long> roleIdList=user.getRoleIdList();
		String sysId = user.getSysId();
		SysUserVo user1=queryObject(user.getUserId());
		if(StringUtils.isBlank(user1.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(user1.getPassword());
		}
		if (sysId != null && !sysId.equals("")) 
			user.setSysId(sysId);
		sysUserBo.update(user);
		if (roleIdList != null && roleIdList.size() > 0) {
			//保存用户与角色关系
			sysUserRoleBo.saveOrUpdate(user.getUserId(), roleIdList);
		}
	}

	@Override
	public void deleteBatch(Long[] userId) {
		sysUserBo.deleteBatch(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		SysUser user = sysUserBo.loadSysUser(userId);
		if(user==null) 
			return 0;
		if(!password.equals(user.getPassword()))
			return 0;
		user.setPassword(newPassword);
		sysUserBo.update(user);
		return 1;
	}

	public ISysUserBo getSysUserBo() {
		return sysUserBo;
	}

	public void setSysUserBo(ISysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}

	public ISysUserRoleBo getSysUserRoleBo() {
		return sysUserRoleBo;
	}

	public void setSysUserRoleBo(ISysUserRoleBo sysUserRoleBo) {
		this.sysUserRoleBo = sysUserRoleBo;
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		 return sysUserRoleBo.queryRoleIdList(userId);				 
	} 
}
