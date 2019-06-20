package com.fescotech.apps.idm.admin.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.admin.biz.bo.ISysMenuBo;
import com.fescotech.apps.idm.admin.domain.SysMenu;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;
public class SysMenuBo extends AbstractBoT implements ISysMenuBo{
	public void insert(SysMenu sysMenu){
		baseDao.insert("sysMenuSql.insert",sysMenu);
	}
	
	public void update(SysMenu sysMenu){
		baseDao.update("sysMenuSql.update",sysMenu);
	}
	
	public List<SysMenuVo> querySysMenu(SysMenu sysMenu){
		return baseDao.queryForList("sysMenuSql.querySysMenu",sysMenu,SysMenuVo.class);
	}
	
	public SysMenu loadSysMenu(Long menuId){
		return (SysMenu)baseDao.queryForObject("sysMenuSql.queryByKey",menuId);
	}

	@Override
	public List<SysMenuVo> queryListParentId(Long parentId) {
		return baseDao.queryForList("sysMenuSql.queryListParentId",parentId,SysMenuVo.class);
	}

	@Override
	public List<SysMenuVo> queryNotButtonList() {
		return baseDao.queryForList("sysMenuSql.queryNotButtonList",null,SysMenuVo.class);
	}

	@Override
	public List<SysMenuVo> queryList(Map<String, Object> map) {
		return baseDao.queryForList("sysMenuSql.queryList",map,SysMenuVo.class);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return (int)baseDao.queryForObject("sysMenuSql.queryTotal",map);
	}

	@Override
	public void deleteBatch(Long[] menuIds) {
		baseDao.delete("sysMenuSql.deleteMenu", menuIds);		
		baseDao.delete("sysMenuSql.deleteRoleMenu", menuIds);		
	}

	@Override
	public List<SysMenuVo> queryList(SysMenu sysMenu, int offset, int limit) {
		 return baseDao.queryForList("sysMenuSql.querySysMenu",sysMenu,SysMenuVo.class,offset,limit);
	}
}