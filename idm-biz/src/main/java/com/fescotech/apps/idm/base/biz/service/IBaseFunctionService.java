package com.fescotech.apps.idm.base.biz.service;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.BaseRoleFunction;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;

/**
 * 授权功能管理service
 * @author:lzl
 * @time:2017年6月19日 下午6:57:32
 */
public interface IBaseFunctionService {
	
	/**
	 * 查询权限功能列表
	 * @param baseFunction
	 * @return
	 */
	public List<BaseFunctionVo> queryList(BaseFunction baseFunction);
	
	/**
	 * 删除权限功能
	 * @param roleId
	 */
	public void deleteRoleFunction(Long roleId);
	
	/**
	 * 新增权限功能
	 * @param baseRoleFunction
	 */
	public void insertRoleFunction(BaseRoleFunction baseRoleFunction);
	
	/**
	 * 获取权限功能总数
	 * @param baseRoleFunction
	 * @return
	 */
	public int queryBaseFunctionCount(BaseFunction baseFunction);

	/**
	 * 权限管理列表展示（分页）
	 * @param map
	 * @return
	 */
	public List<BaseFunctionVo> queryPagelist(Map<String, Object> map);
	
	/**
	 * 根据id查询功能
	 * @return
	 */
	public BaseFunctionVo queryFunctionById(Long functionId);
	
	/**
	 * 新增功能
	 * @param baseFunctionVo
	 */
	public void insertFunction(BaseFunctionVo baseFunctionVo);
	
	/**
	 * 新增功能
	 * @param baseFunctionVo
	 */
	public void updateFunction(BaseFunctionVo baseFunctionVo);
	
	/**
	 * 删除功能
	 * @param baseFunctionVo
	 */
	public void deleteBatch(Long[] functionIds);
}