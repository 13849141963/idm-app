package com.fescotech.apps.idm.base.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.base.domain.vo.BaseOrganizationVo;

public interface IBaseOrganizationBo {
	void insert(BaseOrganization baseOrganization);
	
	void update(BaseOrganization baseOrganization);
	
	List<BaseOrganization> queryBaseOrganization(BaseOrganization baseOrganization);
	
	BaseOrganization loadBaseOrganization(Long id);
	
	List<BaseOrganizationVo> queryTreeList();

	List<BaseOrganization> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	BaseOrganization get(Long orgId);

	void deleteBatch(Long[] orgIds);

	List<BaseOrganization> queryAllOrgList(Map<String, Object> map);

	List<BaseOrganization> queryOrgList(Map<String, Object> map);
}