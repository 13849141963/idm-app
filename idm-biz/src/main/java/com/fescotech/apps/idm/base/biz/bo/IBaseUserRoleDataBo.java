package com.fescotech.apps.idm.base.biz.bo;
import com.fescotech.apps.idm.base.domain.BaseUserRoleData;
import java.util.List;
import java.math.BigDecimal;
public interface IBaseUserRoleDataBo {
	public void insert(BaseUserRoleData baseUserRoleData);
	
	public void update(BaseUserRoleData baseUserRoleData);
	
	public List<BaseUserRoleData> queryBaseUserRoleData(BaseUserRoleData baseUserRoleData);

	public BaseUserRoleData loadBaseUserRoleData(Long baseUserRoleDataId);
	
	public int queryBaseUserRoleDataCount(BaseUserRoleData baseUserRoleData);
}