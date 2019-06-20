package com.fescotech.apps.idm.admin.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.admin.biz.bo.ISysUserBo;
import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
public class SysUserBo extends AbstractBoT implements ISysUserBo{
	public void insert(SysUser sysUser){
		baseDao.insert("sysUserSql.insert",sysUser);
	}
	
	public void update(SysUser sysUser){
		baseDao.update("sysUserSql.update",sysUser);
	}
	
	public List<SysUser> querySysUser(SysUser sysUser){
		return baseDao.queryForList("sysUserSql.querySysUser",sysUser,SysUser.class);
	}
	
	public SysUserVo loadSysUser(Long userId){
		return (SysUserVo)baseDao.queryForObject("sysUserSql.queryByKey",userId);
	}

	@Override
	public List<SysUser> queryList(Map<String, Object> queryData) {
		return baseDao.queryForList("sysUserSql.queryList",queryData,SysUser.class);
	}

	@Override
	public int queryTotal(Map<String, Object> queryData) {		 
		return (int) baseDao.queryForObject("sysUserSql.queryTotal",queryData);
	}

	@Override
	public SysUser queryByUserName(String username) {
		return (SysUser)baseDao.queryForObject("sysUserSql.queryByUserName",username);
	}

	@Override
	public void deleteBatch(Long[] userIds) {
		 baseDao.delete("sysUserSql.deleteUser", userIds);
		 baseDao.delete("sysUserSql.deleteUserRole", userIds);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseDao.queryForList("sysUserSql.queryAllPerms",userId,String.class);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseDao.queryForList("sysUserSql.queryAllMenuId",userId,Long.class);
	}
}