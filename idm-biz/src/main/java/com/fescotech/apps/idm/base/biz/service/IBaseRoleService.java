package com.fescotech.apps.idm.base.biz.service;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseDict;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.vo.BaseRoleVo;

/**
 * 角色管理service
 * @author:lzl
 * @time:2017年6月19日 下午6:44:37
 */
public interface IBaseRoleService {
	
	/**
	 * 新增角色信息
	 * @param baseRole
	 */
	public void insert(BaseRole baseRole);
	
	/**
	 * 更新角色信息
	 * @param baseRole
	 */
	public void update(BaseRole baseRole);
	
	/**
	 * 获取角色信息（列表）
	 * @param baseRole
	 * @return
	 */
	public List<BaseRole> queryBaseRole(BaseRole baseRole);

	/**
	 * 根据角色id获取角色信息
	 * @param roleId
	 * @return
	 */
	public BaseRoleVo loadBaseRole(Long roleId);
	
	/**
	 * 获取角色总数
	 * @param baseRole
	 * @return
	 */
	public int queryBaseRoleCount(BaseRole baseRole);
	
	/**
	 * 角色管理列表展示（分页）
	 * @param map
	 * @return
	 */
	public List<BaseRoleVo> queryList(Map<String, Object> map);
	
	/**
	 * 批量删除角色
	 * @param roleIds
	 */
	public void deleteBatch(Long[] roleIds);
	
	/**
	 * 根据角色id获取已授权信息
	 * @param roleId
	 * @return
	 */
	public List<Long> queryFuncIdList(Long roleId);
	
	/**
	 * 获取角色状态名称
	 * @param baseDict
	 * @return
	 */
	public String queryRoleStatusName(BaseDict baseDict);
	
	/**
	 * 批量删除角色功能中间表
	 * @param roleIds
	 */
	public void deleteRoleFunction(Long[] roleIds);

	BaseRole findBaseRole(BaseRole baseRole);
	
}