package com.fescotech.apps.idm.base.biz.service;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.base.domain.vo.BaseOrganizationVo;

public interface IBaseOrganizationService {
	
	List<BaseOrganizationVo> queryTreeList();

	List<BaseOrganization> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	BaseOrganization queryObject(Long orgId);

	void save(BaseOrganization org);

	void update(BaseOrganization org);

	void deleteBatch(Long[] orgIds);

	List<BaseOrganization> queryAllOrgList(Map<String, Object> map);

	List<BaseOrganization> queryAllOrg(BaseOrganization org);

	List<BaseOrganization> queryOrgList(Map<String, Object> map);

}
