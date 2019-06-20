package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fescotech.apps.idm.base.biz.bo.IBaseFunctionBo;
import com.fescotech.apps.idm.base.biz.bo.IBaseRoleFunctionBo;
import com.fescotech.apps.idm.base.biz.service.IBaseFunctionService;
import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.BaseRoleFunction;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;

/**
 * 授权功能管理service实现类
 * @author:lzl
 * @time:2017年6月19日 下午7:36:25
 */
@Service("baseFunctionService")
public class BaseFunctionService implements IBaseFunctionService{

	/**
	 * 功能管理Bo
	 */
	private IBaseFunctionBo baseFunctionBo;
	
	/**
	 * 角色功能管理Bo
	 */
	private IBaseRoleFunctionBo baseRoleFunctionBo;
	
	/**
	 * 设置角色功能
	 * @param baseRoleFunctionBo
	 */
	public void setBaseRoleFunctionBo(IBaseRoleFunctionBo baseRoleFunctionBo) {
		this.baseRoleFunctionBo = baseRoleFunctionBo;
	}

	/**
	 * 设置功能
	 * @param baseFunctionBo
	 */
	public void setBaseFunctionBo(IBaseFunctionBo baseFunctionBo) {
		this.baseFunctionBo = baseFunctionBo;
	}

	/**
	 * 查询权限功能列表
	 * @param baseFunction
	 * @return
	 */
	@Override
	public List<BaseFunctionVo> queryList(BaseFunction baseFunction) {
		return baseFunctionBo.queryBaseFunction(baseFunction);
	}

	/**
	 * 删除权限功能
	 * @param roleId
	 */
	@Override
	public void deleteRoleFunction(Long roleId) {
		baseRoleFunctionBo.deleteRoleFunction(roleId);
	}

	/**
	 * 新增权限功能
	 * @param baseRoleFunction
	 */
	@Override
	public void insertRoleFunction(BaseRoleFunction baseRoleFunction) {
		baseRoleFunctionBo.insertRoleEubction(baseRoleFunction);
	}
	
	/**
	 * 权限管理列表展示（分页）
	 * @param map
	 * @return
	 */
	@Override
	public List<BaseFunctionVo> queryPagelist(Map<String, Object> map) {
		return baseFunctionBo.queryList(map);
	}

	/**
	 * 获取权限功能总数
	 * @param baseRoleFunction
	 * @return
	 */
	@Override
	public int queryBaseFunctionCount(BaseFunction baseFunction) {
		return baseFunctionBo.queryBaseFunctionCount(baseFunction);
	}

	/**
	 * 根据id查询功能
	 * @return
	 */
	@Override
	public BaseFunctionVo queryFunctionById(Long functionId) {
		return baseFunctionBo.loadBaseFunction(functionId);
	}

	/**
	 * 新增功能
	 * @param baseFunctionVo
	 */
	@Override
	public void insertFunction(BaseFunctionVo baseFunctionVo) {
		baseFunctionBo.insert(baseFunctionVo);
	}

	/**
	 * 修改功能
	 * @param baseFunctionVo
	 */
	@Override
	public void updateFunction(BaseFunctionVo baseFunctionVo) {
		baseFunctionBo.update(baseFunctionVo);
	}

	/**
	 * 删除功能
	 * @param baseFunctionVo
	 */
	@Override
	public void deleteBatch(Long[] functionIds) {
		baseFunctionBo.deleteBatch(functionIds);
	}

}
