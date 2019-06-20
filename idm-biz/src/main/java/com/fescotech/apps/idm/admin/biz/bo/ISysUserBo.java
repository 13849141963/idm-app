package com.fescotech.apps.idm.admin.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
public interface ISysUserBo {
	public void insert(SysUser sysUser);
	
	public void update(SysUser sysUser);
	
	public List<SysUser> querySysUser(SysUser sysUser);
	
	public List<SysUser> queryList(Map<String, Object> queryData);
	
	public int queryTotal(Map<String,Object> queryData);
	
	public SysUser queryByUserName(String username);
	
	public SysUserVo loadSysUser(Long userId);
	
	public void deleteBatch(Long[] userId);
	
	public List<String> queryAllPerms(Long userId);
 
	public List<Long> queryAllMenuId(Long userId);

}