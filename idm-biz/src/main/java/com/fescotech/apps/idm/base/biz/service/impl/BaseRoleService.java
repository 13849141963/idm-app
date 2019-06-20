package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fescotech.apps.idm.base.biz.bo.IBaseDictBo;
import com.fescotech.apps.idm.base.biz.bo.IBaseRoleBo;
import com.fescotech.apps.idm.base.biz.bo.IBaseRoleFunctionBo;
import com.fescotech.apps.idm.base.biz.service.IBaseRoleService;
import com.fescotech.apps.idm.base.domain.BaseDict;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.vo.BaseRoleVo;

/**
 * 角色管理service实现类
 * @author:lzl
 * @time:2017年6月19日 下午6:51:14
 */
@Service("baseRoleService")
public class BaseRoleService implements IBaseRoleService{

	/**
	 * 角色管理Bo
	 */
	private IBaseRoleBo baseRoleBo;
	
	/**
	 * 角色与功能管理Bo
	 */
	private IBaseRoleFunctionBo baseRoleFunctionBo;
	
	/**
	 * 字典管理Bo
	 */
	private IBaseDictBo baseDictBo;
	
	/**
	 * 设置字典管理Bo
	 * @param baseDictBo
	 */
	public void setBaseDictBo(IBaseDictBo baseDictBo) {
		this.baseDictBo = baseDictBo;
	}

	/**
	 * 设置角色管理Bo
	 * @param baseRoleBo
	 */
	public void setBaseRoleBo(IBaseRoleBo baseRoleBo) {
		this.baseRoleBo = baseRoleBo;
	}

	/**
	 * 设置角色与功能Bo
	 * @param baseRoleFunctionBo
	 */
	public void setBaseRoleFunctionBo(IBaseRoleFunctionBo baseRoleFunctionBo) {
		this.baseRoleFunctionBo = baseRoleFunctionBo;
	}

	/**
	 * 新增角色信息
	 * @param baseRole
	 */
	@Override
	public void insert(BaseRole baseRole) {
		baseRoleBo.insert(baseRole);
	}

	/**
	 * 更新角色信息
	 * @param baseRole
	 */
	@Override
	public void update(BaseRole baseRole) {
		baseRoleBo.update(baseRole);
	}

	/**
	 * 获取角色信息（列表）
	 * @param baseRole
	 * @return
	 */
	@Override
	public List<BaseRole> queryBaseRole(BaseRole baseRole) {
		// TODO Auto-generated method stub
		return baseRoleBo.queryBaseRole(baseRole);
	}
	
	@Override
	public BaseRole findBaseRole(BaseRole baseRole) {
		// TODO Auto-generated method stub
		return baseRoleBo.findBaseRole(baseRole);
	}

	/**
	 * 根据角色id获取角色信息
	 * @param roleId
	 * @return
	 */
	@Override
	public BaseRoleVo loadBaseRole(Long roleId) {
		return baseRoleBo.loadBaseRole(roleId);
	}

	/**
	 * 获取角色总数
	 * @param baseRole
	 * @return
	 */
	@Override
	public int queryBaseRoleCount(BaseRole baseRole) {
		return baseRoleBo.queryBaseRoleCount(baseRole);
	}

	/**
	 * 角色管理列表展示（分页）
	 * @param map
	 * @return
	 */
	@Override
	public List<BaseRoleVo> queryList(Map<String, Object> map) {
		return baseRoleBo.queryList(map);
	}

	/**
	 * 批量删除角色
	 * @param roleIds
	 */
	@Override
	public void deleteBatch(Long[] roleIds) {
		baseRoleBo.deleteBatch(roleIds);
	}

	/**
	 * 根据角色id获取已授权信息
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Long> queryFuncIdList(Long roleId) {
		return baseRoleFunctionBo.queryFuncIdList(roleId);
	}

	/**
	 * 获取角色状态名称
	 * @param baseDict
	 * @return
	 */
	@Override
	public String queryRoleStatusName(BaseDict baseDict) {
		BaseDict dict = baseDictBo.queryDictItem(baseDict.getDictType(),baseDict.getDictCode());
		if(dict==null)return null;
		return dict.getDictName();
	}

	/**
	 * 批量删除角色功能中间表
	 * @param roleIds
	 */
	@Override
	public void deleteRoleFunction(Long[] roleIds) {
		baseRoleFunctionBo.deleteBatch(roleIds);
	}

}
