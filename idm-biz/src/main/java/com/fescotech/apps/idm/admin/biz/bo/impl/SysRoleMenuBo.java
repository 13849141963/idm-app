package com.fescotech.apps.idm.admin.biz.bo.impl;
import java.util.List;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.admin.biz.bo.ISysRoleMenuBo;
import com.fescotech.apps.idm.admin.domain.SysRoleMenu;
public class SysRoleMenuBo extends AbstractBoT implements ISysRoleMenuBo{
	public void insert(SysRoleMenu sysRoleMenu){
		baseDao.insert("sysRoleMenuSql.insert",sysRoleMenu);
	}
	
	public void update(SysRoleMenu sysRoleMenu){
		baseDao.update("sysRoleMenuSql.update",sysRoleMenu);
	}
	
	public List<SysRoleMenu> querySysRoleMenu(SysRoleMenu sysRoleMenu){
		return baseDao.queryForList("sysRoleMenuSql.querySysRoleMenu",sysRoleMenu,SysRoleMenu.class);
	}
	
	public SysRoleMenu loadSysRoleMenu(Long id){
		return (SysRoleMenu)baseDao.queryForObject("sysRoleMenuSql.queryByKey",id);
	}

	@Override
	public int deleteByRoleId(Long roleId) {
		return  baseDao.delete("sysRoleMenuSql.deleteByRoleId", roleId);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return baseDao.queryForList("sysRoleMenuSql.queryMenuIdList",roleId,Long.class);
	}

	@Override
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//先删除角色与菜单关系
		this.deleteByRoleId(roleId);
		//保存角色与菜单关系
		for(Long menuId : menuIdList){
			SysRoleMenu srm = new SysRoleMenu();
			srm.setMenuId(menuId);
			srm.setRoleId(roleId);
			this.insert(srm);
		}
	}

}