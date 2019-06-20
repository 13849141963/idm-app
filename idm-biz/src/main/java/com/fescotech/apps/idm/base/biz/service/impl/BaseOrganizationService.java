package com.fescotech.apps.idm.base.biz.service.impl;
import java.util.List;
import java.util.Map;
import com.fescotech.apps.idm.base.biz.bo.IBaseOrganizationBo;
import com.fescotech.apps.idm.base.biz.service.IBaseOrganizationService;
import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.base.domain.vo.BaseOrganizationVo;

public class BaseOrganizationService implements IBaseOrganizationService {
	
	private IBaseOrganizationBo baseOrganizationBo;

	public void setBaseOrganizationBo(IBaseOrganizationBo baseOrganizationBo) {
		this.baseOrganizationBo = baseOrganizationBo;
	}

	@Override
	public List<BaseOrganizationVo> queryTreeList() {
		
		return baseOrganizationBo.queryTreeList();
	}

	@Override
	public List<BaseOrganization> queryList(Map<String, Object> map) {		
		return baseOrganizationBo.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return baseOrganizationBo.queryTotal(map);
	}

	@Override
	public BaseOrganization queryObject(Long orgId) {
		return baseOrganizationBo.get(orgId);
	}

	@Override
	public void save(BaseOrganization org) {
		baseOrganizationBo.insert(org);		
	}

	@Override
	public void update(BaseOrganization org) {
		baseOrganizationBo.update(org);
	}

	@Override
	public void deleteBatch(Long[] orgIds) {
		baseOrganizationBo.deleteBatch(orgIds);
	}

	//点击一个orgId，获取他的所有org
	@Override
	public List<BaseOrganization> queryAllOrgList(Map<String, Object> map) {
		
		return baseOrganizationBo.queryAllOrgList(map);
	}
	
	@Override
	public List<BaseOrganization> queryAllOrg(BaseOrganization org) {
		return baseOrganizationBo.queryBaseOrganization(org);
	}

	
	@Override
	public List<BaseOrganization> queryOrgList(Map<String, Object> map) {		
		return baseOrganizationBo.queryOrgList(map);
	}
	
}
