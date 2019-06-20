package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseOrganizationBo;
import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.base.domain.vo.BaseOrganizationVo;

public class BaseOrganizationBo extends AbstractBoT implements IBaseOrganizationBo{

	public void insert(BaseOrganization baseOrganization){
		baseDao.insert("baseOrganizationSql.insert",baseOrganization);
	}
	
	public void update(BaseOrganization baseOrganization){
		baseDao.update("baseOrganizationSql.update",baseOrganization);
	}
	
	public List<BaseOrganization> queryBaseOrganization(BaseOrganization baseOrganization){
		return baseDao.queryForList("baseOrganizationSql.queryBaseOrganization",baseOrganization,BaseOrganization.class);
	}
	
	public BaseOrganization loadBaseOrganization(Long id){
		return (BaseOrganization)baseDao.queryForObject("baseOrganizationSql.queryByKey",id);
	}
	
	@Override
	public List<BaseOrganizationVo> queryTreeList() {
		return baseDao.queryForList("baseOrganizationSql.queryTreeList",null,BaseOrganizationVo.class);
	}

	@Override
	public List<BaseOrganization> queryList(Map<String, Object> map) {
		return baseDao.queryForList("baseOrganizationSql.queryList",map,BaseOrganization.class);
	}
	
	@Override
	public List<BaseOrganization> queryOrgList(Map<String, Object> map) {
		return baseDao.queryForList("baseOrganizationSql.queryOrgList",map,BaseOrganization.class);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return (int)baseDao.queryForObject("baseOrganizationSql.queryTotal",map);
	}

	@Override
	public BaseOrganization get(Long orgId) {
		return (BaseOrganization)baseDao.queryForObject("baseOrganizationSql.queryByKey",orgId);
	}

	@Override
	public void deleteBatch(Long[] orgIds) {
		baseDao.delete("baseOrganizationSql.deleteBatch", orgIds);	
		
	}
	
	//根据一个orgId查询出所有的子集合
	@Override
	public List<BaseOrganization> queryAllOrgList(Map<String, Object> map) {
		return baseDao.queryForList("baseOrganizationSql.queryAllOrgList",map,BaseOrganization.class);
	}
}