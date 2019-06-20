package com.fescotech.apps.idm.admin.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysMenu;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;

public interface ISysMenuBo {
	public void insert(SysMenu sysMenu);
	
	public void update(SysMenu sysMenu);
	
	public List<SysMenuVo> querySysMenu(SysMenu sysMenu);
	
	public SysMenu loadSysMenu(Long menuId);
	
	public List<SysMenuVo> queryListParentId(Long parentId);
	
	public List<SysMenuVo> queryNotButtonList();
	
	public List<SysMenuVo> queryList(Map<String, Object> map);
	
	public List<SysMenuVo> queryList(SysMenu sysMenu,int offset,int limit);
	
	public int queryTotal(Map<String, Object> map);
	
	public void deleteBatch(Long[] menuIds);
}