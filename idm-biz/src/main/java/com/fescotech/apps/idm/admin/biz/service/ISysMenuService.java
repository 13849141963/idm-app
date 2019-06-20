package com.fescotech.apps.idm.admin.biz.service;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysMenu;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;
/**
 * 菜单管理
 */
public interface ISysMenuService {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenuVo> queryListParentId(Long parentId, List<Long> menuIdList);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuVo> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenuVo> getUserMenuList(Long userId);
	
	
	/**
	 * 查询菜单
	 */
	SysMenu queryObject(Long menuId);
	
	/**
	 * 查询菜单列表
	 */
	List<SysMenuVo> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存菜单
	 */
	void save(SysMenu menu);
	
	/**
	 * 修改
	 */
	void update(SysMenu menu);
	
	/**
	 * 删除
	 */
	void deleteBatch(Long[] menuIds);
}
